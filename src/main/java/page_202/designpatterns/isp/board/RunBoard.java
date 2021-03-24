package page_202.designpatterns.isp.board;

interface UserCommand {
    void create();
    void update();
    void read();

}

interface AdminCommand {
    void create();
    void update();
    void read();
    void delete();
}


class User {

    private UserCommand userCommand;

    public void create() {
        userCommand.create();
    }
    public void update() {
        userCommand.update();
    }
    public void read() {
        userCommand.read();
    }

}

class Admin {
    private AdminCommand adminCommand;

    public void create() {
        adminCommand.create();
    }
    public void update() {
        adminCommand.update();
    }
    public void read() {
        adminCommand.read();
    }
    public void delete() {
        adminCommand.delete();
    }
}


class Board implements UserCommand, AdminCommand {

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void read() {

    }

    @Override
    public void delete() {

    }
}

public class RunBoard {

    public static void main(String[] args) {
        User user = new User();

        Admin admin = new Admin();
    }
}
