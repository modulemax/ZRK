package com.zhengrenkun.zrk.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.gc.materialdesign.views.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.widgets.ProgressDialog;
import com.zhengrenkun.zrk.ChatService;
import com.zhengrenkun.zrk.R;
import com.zhengrenkun.zrk.chat.ChatServerManage;
import com.zhengrenkun.zrk.chat.model.JoinMsg;

import io.socket.client.Ack;

/**
 * A login screen that offers login via email/password.
 */
public class SignInActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mOpenid;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mOpenid = (EditText) findViewById(R.id.openid);
        startService(new Intent(this, ChatService.class));
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mProgressDialog=new ProgressDialog(this,"正在登录");
    }


    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mOpenid.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String openid = mOpenid.getText().toString();

        mProgressDialog.show();
        ChatServerManage.getChatServerManage().emitJoin(new JoinMsg(email,openid), new Ack() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressDialog.hide();
                        Toast.makeText(SignInActivity.this,args[0].toString(),Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(SignInActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mProgressDialog.isShowing()){mProgressDialog.hide();}
        return super.onKeyDown(keyCode, event);
    }
}

