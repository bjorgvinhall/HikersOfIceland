package is.hi.hbv601g.hikers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signInButton = findViewById(R.id.login);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        Button signUpButton = findViewById(R.id.signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signIn() {
        Intent intent;
        intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void signUp() {
        // TODO
        Toast.makeText(getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();
    }

}
