package page_112.app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class MessageRepositoryUsingSpringJDBC {

    private final static Log log = LogFactory.getLog(MessageRepositoryUsingSpringJDBC.class);

    private NamedParameterJdbcTemplate jdbcTemplate;

    public MessageRepositoryUsingSpringJDBC(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Message saveMessage(Message message) {

        // 생성 될 id를 보관할 객체 (auto_increment_value)
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        String insertSql = "insert into messages(`id`, `text`, `created_date`) value(null, :text, :createdDate)";

        // paramName 과 insertSql 의 :매개변수 명이 일치해야한다
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("text", message.getText());
        params.addValue("createdDate", message.getCreatedDate());


        try {
            // sql 구문, 매개변수, id가 저장될 객체를 넣어 update 쿼리문을 실행한다
            this.jdbcTemplate.update(insertSql, params, holder);
        } catch (DataAccessException e) {
            log.error("메시지를 저장하지 못했습니다.", e);
            return null;
        }
        return new Message(holder.getKey().intValue(), message.getText(), message.getCreatedDate());
    }
}
