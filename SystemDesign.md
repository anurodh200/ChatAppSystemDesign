# Chat Application - Low Level Design Documentation

## Table of Contents
1. [Overview](#overview)
2. [System Components](#system-components)
3. [Class Details](#class-details)
4. [Design Patterns & Principles](#design-patterns--principles)
5. [Implementation Guide](#implementation-guide)

## Overview

This document outlines the low-level design of a chat application that supports:
- Private messaging between users
- Group chat functionality
- Friend request system
- User management

## System Components

### Core Services
- **UserService**: Central management of user operations
- **Chat System**: Handles message exchange
- **Friend Request System**: Manages social connections

### Data Models
- Users
- Messages
- Chat Rooms (Private & Group)
- Friend Requests

## Class Details

### 1. UserService Class
```java
class UserService {
    private Map usersById = new HashMap<>();

    // User Management
    public void addUser(int userId, String name, String passHash)
    public void removeUser(int userId)
    
    // Friend Request Management
    public void addFriendRequest(int fromUserId, int toUserId)
    public void approveFriendRequest(int fromUserId, int toUserId)
    public void rejectFriendRequest(int fromUserId, int toUserId)
}
```

**Responsibilities:**
- User registration and removal
- Friend request processing
- User relationship management

### 2. User Class
```java
class User {
    private int userId;
    private String name;
    private String passHash;
    private Map friends;
    private Map privateChats;
    private Map groupChats;
    private Map receivedFriendRequests;
    private Map sentFriendRequests;

    // Messaging Methods
    public void messageUser(int friendId, String message)
    public void messageGroup(int groupId, String message)
    
    // Friend Request Methods
    public void sendFriendRequest(AddRequest request)
    public void receiveFriendRequest(AddRequest request)
    public void approveFriendRequest(int friendId)
    public void rejectFriendRequest(int friendId)
}
```

**Key Features:**
- Personal information storage
- Friend list management
- Chat participation
- Friend request handling

### 3. Chat System

#### Base Chat Class
```java
abstract class Chat {
    protected int chatId;
    protected List users;
    protected List messages;

    public void addMessage(Message message)
}
```

#### Private Chat Class
```java
class PrivateChat extends Chat {
    public PrivateChat(int chatId, User user1, User user2)
}
```

#### Group Chat Class
```java
class GroupChat extends Chat {
    public void addUser(User user)
    public void removeUser(User user)
}
```

### 4. Message Class
```java
class Message {
    private String messageId;
    private String message;
    private Date timestamp;
}
```

### 5. Friend Request System
```java
class AddRequest {
    private int fromUserId;
    private int toUserId;
    private RequestStatus status;
    private Date timestamp;
    private User fromUser;
}

enum RequestStatus {
    UNREAD,
    READ,
    ACCEPTED,
    REJECTED
}
```

## Design Patterns & Principles

### 1. SOLID Principles Application
- **Single Responsibility**: Each class has a focused purpose
- **Open/Closed**: Chat system is extensible through inheritance
- **Liskov Substitution**: Chat subtypes can be used interchangeably
- **Interface Segregation**: Clean separation of chat functionalities
- **Dependency Inversion**: High-level modules don't depend on details

### 2. Design Patterns Used
- **Observer Pattern**: For message notifications
- **Factory Pattern**: For creating chat instances
- **Strategy Pattern**: For message handling

## Implementation Guide

### Setting Up User Management
1. Initialize UserService
2. Add user registration functionality
3. Implement authentication system

### Implementing Chat Features
1. Create base Chat class
2. Extend for PrivateChat and GroupChat
3. Implement message handling

### Friend Request System
1. Create AddRequest handling
2. Implement request notifications
3. Add request processing logic

### Data Storage Considerations
- Use in-memory storage for development
- Plan for database integration
- Consider caching strategies

### Security Implementation
1. Password hashing
2. Session management
3. Message encryption

## Technical Considerations

### Scalability
- Implement database sharding
- Use message queues for async processing
- Add caching layer

### Performance
- Optimize friend list lookups
- Implement efficient message delivery
- Use connection pooling

### Security
- Implement end-to-end encryption
- Add rate limiting
- Secure user authentication

## Future Enhancements

### 1. Message Features
- Read receipts
- Message editing
- File sharing
- Voice messages

### 2. Group Chat Features
- Admin roles
- User permissions
- Chat rules
- Group invitations

### 3. User Features
- User profiles
- Status updates
- Last seen status
- Typing indicators
