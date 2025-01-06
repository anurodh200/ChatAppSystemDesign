import java.util.*;

class UserService {
    private Map<Integer, User> usersById = new HashMap<>();

    // Add a new user
    public void addUser(int userId, String name, String passHash) {
        usersById.put(userId, new User(userId, name, passHash));
    }

    // Remove an existing user
    public void removeUser(int userId) {
        usersById.remove(userId);
    }

    // Send a friend request
    public void addFriendRequest(int fromUserId, int toUserId) {
        User fromUser = usersById.get(fromUserId);
        User toUser = usersById.get(toUserId);
        if (fromUser != null && toUser != null) {
            AddRequest request = new AddRequest(fromUserId, toUserId, RequestStatus.UNREAD, new Date(), fromUser);
            toUser.receiveFriendRequest(request);
            fromUser.sendFriendRequest(request);
        }
    }

    // Approve a friend request
    public void approveFriendRequest(int fromUserId, int toUserId) {
        User fromUser = usersById.get(fromUserId);
        User toUser = usersById.get(toUserId);
        if (fromUser != null && toUser != null) {
            fromUser.approveFriendRequest(toUserId);
            toUser.approveFriendRequest(fromUserId);
        }
    }

    // Reject a friend request
    public void rejectFriendRequest(int fromUserId, int toUserId) {
        User fromUser = usersById.get(fromUserId);
        User toUser = usersById.get(toUserId);
        if (fromUser != null && toUser != null) {
            fromUser.rejectFriendRequest(toUserId);
            toUser.rejectFriendRequest(fromUserId);
        }
    }
}

class User {
    private int userId;
    private String name;
    private String passHash;
    private Map<Integer, User> friends = new HashMap<>();
    private Map<Integer, PrivateChat> privateChats = new HashMap<>();
    private Map<Integer, GroupChat> groupChats = new HashMap<>();
    private Map<Integer, AddRequest> receivedFriendRequests = new HashMap<>();
    private Map<Integer, AddRequest> sentFriendRequests = new HashMap<>();

    public User(int userId, String name, String passHash) {
        this.userId = userId;
        this.name = name;
        this.passHash = passHash;
    }

    public void messageUser(int friendId, String message) {
        PrivateChat chat = privateChats.get(friendId);
        if (chat != null) {
            chat.addMessage(new Message(UUID.randomUUID().toString(), message, new Date()));
        }
    }

    public void messageGroup(int groupId, String message) {
        GroupChat chat = groupChats.get(groupId);
        if (chat != null) {
            chat.addMessage(new Message(UUID.randomUUID().toString(), message, new Date()));
        }
    }

    public void sendFriendRequest(AddRequest request) {
        sentFriendRequests.put(request.getToUserId(), request);
    }

    public void receiveFriendRequest(AddRequest request) {
        receivedFriendRequests.put(request.getFromUserId(), request);
    }

    public void approveFriendRequest(int friendId) {
        if (receivedFriendRequests.containsKey(friendId)) {
            friends.put(friendId, receivedFriendRequests.get(friendId).getFromUser());
            receivedFriendRequests.remove(friendId);
        }
    }

    public void rejectFriendRequest(int friendId) {
        receivedFriendRequests.remove(friendId);
    }
}

abstract class Chat {
    protected int chatId;
    protected List<User> users = new ArrayList<>();
    protected List<Message> messages = new ArrayList<>();

    public Chat(int chatId) {
        this.chatId = chatId;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}

class PrivateChat extends Chat {
    public PrivateChat(int chatId, User user1, User user2) {
        super(chatId);
        users.add(user1);
        users.add(user2);
    }
}

class GroupChat extends Chat {
    public GroupChat(int chatId) {
        super(chatId);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}

class Message {
    private String messageId;
    private String message;
    private Date timestamp;

    public Message(String messageId, String message, Date timestamp) {
        this.messageId = messageId;
        this.message = message;
        this.timestamp = timestamp;
    }
}

class AddRequest {
    private int fromUserId;
    private int toUserId;
    private RequestStatus status;
    private Date timestamp;
    private User fromUser;

    public AddRequest(int fromUserId, int toUserId, RequestStatus status, Date timestamp, User fromUser) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.status = status;
        this.timestamp = timestamp;
        this.fromUser = fromUser;
    }
    public User getFromUser() {
        return fromUser;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }
}

enum RequestStatus {
    UNREAD, READ, ACCEPTED, REJECTED
}
