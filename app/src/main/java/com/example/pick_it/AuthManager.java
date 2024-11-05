package com.example.pick_it;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class User {
    private String userId;
    private String userPw;

    public User(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    public User() {
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUserPw() {
        return this.userPw;
    }
}

public class AuthManager {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");

    public void registerUser(String userId, String userPw) {
        User user = new User(userId, userPw);
        myRef.child(user.getUserId()).setValue(user);
    }

    public void loginUser(String userId, String userPw) {

    }
}
