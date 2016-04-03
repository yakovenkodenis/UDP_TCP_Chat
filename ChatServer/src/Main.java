import server.udp.UDPServer;


public class Main {

    public static void main(final String[] args) throws Exception {

        System.out.println("HELLO WORLD");

//        try {
//            final ChatUserManager chatUserManager = new ChatUserManager();
//
//            ChatUser user = chatUserManager.getById(2);
//            Hibernate.initialize(user);
//            System.out.println(user.toString());
//
//            user.setLastName("Iakovenko");
//            chatUserManager.updateChatUser(user);
//
//            user = chatUserManager.getById(2);
//            System.out.println(user.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        new UDPServer().run();
    }
}
