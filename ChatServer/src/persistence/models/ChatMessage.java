package persistence.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ChatMessage")
public class ChatMessage implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private ChatUser chatUser;

    @ManyToOne
    @JoinColumn(name = "destination_group_id")
    private ChatGroup chatGroup;

    public ChatMessage() {}

    public ChatMessage(String content, Date createdAt,
                       ChatUser chatUser, ChatGroup chatGroup) {
        this.content = content;
        this.createdAt = createdAt;
        this.chatUser = chatUser;
        this.chatGroup = chatGroup;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ChatUser getChatUser() {
        return chatUser;
    }

    public void setChatUser(ChatUser chatUser) {
        this.chatUser = chatUser;
    }

    public ChatGroup getChatGroup() {
        return chatGroup;
    }

    public void setChatGroup(ChatGroup chatGroup) {
        this.chatGroup = chatGroup;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", content='" + content + '\'' +
                ", chatUser=" + chatUser +
                ", chatGroup=" + chatGroup +
                '}';
    }

    public static void updateAttributes(ChatMessage oldMessage, ChatMessage newMessage) {
        oldMessage.setContent(newMessage.getContent());
        oldMessage.setCreatedAt(newMessage.getCreatedAt());
    }
}
