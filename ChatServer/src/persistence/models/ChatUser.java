package persistence.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ChatUser")
public class ChatUser implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "is_admin")
    private boolean admin;

    @Column(name = "education_level")
    private String educationLevel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chatUser")
    private Set<ChatMessage> chatMessages = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chatUser")
    private Set<ChatGroupMembers> chatGroupMembers = new HashSet<>();

    public ChatUser() {}

    public ChatUser(int id, String firstName, String lastName, String email, String password,
                    Date createdAt, Date updatedAt, boolean admin, String educationLevel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.admin = admin;
        this.educationLevel = educationLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Set<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(Set<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public Set<ChatGroupMembers> getChatGroupMembers() {
        return chatGroupMembers;
    }

    public void setChatGroupMembers(Set<ChatGroupMembers> chatGroupMembers) {
        this.chatGroupMembers = chatGroupMembers;
    }

    @Override
    public String toString() {
        return "ChatUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", admin=" + admin +
                ", educationLevel='" + educationLevel + '\'' +
                '}';
    }

    public static void updateAttributes(ChatUser oldUser, ChatUser newUser) {
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setCreatedAt(newUser.getCreatedAt());
        oldUser.setUpdatedAt(new Date());
        oldUser.setChatMessages(newUser.getChatMessages());
        oldUser.setEducationLevel(newUser.getEducationLevel());
        oldUser.setChatGroupMembers(newUser.getChatGroupMembers());
    }
}
