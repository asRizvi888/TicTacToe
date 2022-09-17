package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton infoBtn = (ImageButton) findViewById(R.id.infoBtn);
        EditText etO = (EditText) findViewById(R.id.etO);
        EditText etX = (EditText) findViewById(R.id.etX);
        Button goBtn = (Button) findViewById(R.id.btnGo);

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p1 = etO.getText().toString();
                String p2 = etX.getText().toString();

                if (p1.length() == 0 || p2.length() == 0) {
                    Toast.makeText(MainActivity.this, "Undefined username found", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);

                    intent.putExtra("p1",p1);
                    intent.putExtra("p2",p2);

                    startActivity(intent);
                }
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(MainActivity.this, "Info", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setTitle("Instructions");
                dialog.setMessage("Tic Tac Toe is a two person game.\n" +
                        "Make sure to choose players for corresponding X & O\n\n" +
                        "Player O gets to make the first move.\n\n" +
                        "Make moves wisely & win it..."
                );
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }
}