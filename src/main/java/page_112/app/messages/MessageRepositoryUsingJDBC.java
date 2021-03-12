package page_112.app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class MessageRepositoryUsingJDBC {

    // 스프링 빈에 등록된 dataSource 빈을 가져온다 (mysql, username, password 설정내역이 담겨짐)
    private DataSource dataSource;

    private final static Log log = LogFactory.getLog(MessageRepositoryUsingJDBC.class);

    public MessageRepositoryUsingJDBC(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    public Message saveMessage(Message message) {

        // 1. 해당 DB에 커넥션을 연결 (데이터 베이스와 연결하기 위해 헬퍼(helper) 인 DataSourceUtils 이용
        Connection c = DataSourceUtils.getConnection(dataSource);

        try {
            // 2. SQL 작성
            String insertSql = "insert into messages(`id`, `text`, `created_date`) value (null, ?, ?)";

            // 3. SQL을 Connection 된 DB에 날릴 객체를 생성
            // (Statement.RETURN_GENERATED_KEYS 는 생성 된 메시지의 id = auto_increment 를 반환하기 위해 사용한다)
            PreparedStatement ps = c.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

            // 4. SQL 에 필요한 매개 변수
            ps.setString(1, message.getText());
            ps.setTimestamp(2, new Timestamp(message.getCreatedDate().getTime()));

            // 5. 쿼리문 전송 = INSERT (입력된 row 의 수 리턴)
            int rowsAffected = ps.executeUpdate();
            log.info(String.format("rowsAffected= %d", rowsAffected));

            // 6. 만약 DB 에 INSERT(삽입) 되었다면
            if (rowsAffected > 0) {
                // 새로 저장된 메시지 id 가져오기 (auto increment 값)
                ResultSet result = ps.getGeneratedKeys();

                // 7. key 가 존재 한다면
                if (result.next()) {
                    log.info(result.getInt(1));
//                    log.info(result.getString(2));

                    // 해당 키의 값을 가져옴
                    int id = result.getInt(1);

                    // 처음에 resquest 에서 들어온 Message는 text와 createdDate 밖에 없지만 DB 에 insert 됨으로써 id가 포함되어 리턴된다
                    return new Message(id, message.getText(), message.getCreatedDate());
                } else {
                    log.error("ID를 검색하지 못했습니다. 결과 세트에 행이 없습니다.");
                    return null;
                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            log.error("메시지를 저장하지 못했습니다.", e);

            try {
                // 커넥션 닫기
                c.close();
            } catch (SQLException e2) {
                log.error("커넥션을 닫지 못했습니다", e2);
            }
        } finally {
            // 커넥션과의 연결을 끊기
            DataSourceUtils.releaseConnection(c, dataSource);
        }

        return null;
    }
}
