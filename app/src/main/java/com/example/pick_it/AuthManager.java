package com.example.pick_it;

import android.util.Log;

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

    public void setUserId(String userId) {
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
        DatabaseReference userRef = myRef.child(userId);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    User user = snapshot.getValue(User.class);
                    if(user != null) {
                        if (user.getUserId().equals(userId) && user.getUserPw().equals(userPw)) {
                            Log.d("User", "로그인 성공");
                        } else {
                            Log.d("User", "아이디 혹은, 비밀번호 오류");
                        }
                    }else {
                        Log.d("User", "로그인실패 : 해당 아이디 존재하지않음");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("User", "로그인실패 : 데이터베이스 오류");
            }
        });
    }
}
