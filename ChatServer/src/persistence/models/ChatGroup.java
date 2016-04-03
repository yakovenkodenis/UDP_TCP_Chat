package persistence.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ChatGroup")
public class ChatGroup implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "group_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chatGroup")
    private Set<ChatMessage> chatMessages = new HashSet<>();

    public ChatGroup() {}

    public ChatGroup(Date createdAt, String name) {
        this.createdAt = createdAt;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(Set<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @Override
    public String toString() {
        return "ChatGroup{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", chatMessages=" + chatMessages +
                '}';
    }
}
