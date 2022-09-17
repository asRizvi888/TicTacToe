package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    String Board[][] = new String[3][3];

    private Button[][] buttons;

    public Button[][] getButtons() {
        return buttons;
    }

    public void setButtons(Button[][] buttons) {
        this.buttons = buttons;
    }

    int move = 0;

    boolean found = false;  // turns true if found user

    // Method to detect player's moves
    public void onChangeState(Button btn, int x, int y, TextView p1, TextView p2) {
        // implement check

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (found == false) {
                    if (btn.getText().toString().length() == 0) {
                        String pos = move % 2 == 0 ? "O" : "X";
                        Board[x][y] = pos;
                        btn.setText(pos);

                        // taking corresponding position in board

                        if (move % 2 != 0) {
                            p1.setTextColor(Color.parseColor("#00796B"));
                            p2.setTextColor(Color.parseColor("#AFAFAF"));
                        } else {
                            p2.setTextColor(Color.parseColor("#00796B"));
                            p1.setTextColor(Color.parseColor("#AFAFAF"));
                        }


                        // implement check functionality
                        Button buttons[][] = getButtons();

                        for (int i=0; i<3; ++i) {
                            check(Board[i][0], Board[i][1], Board[i][2], p1, p2, new Button[]{buttons[i][0], buttons[i][1], buttons[i][2]});
                            check(Board[0][i], Board[1][i], Board[2][i], p1, p2, new Button[]{buttons[0][i], buttons[1][i], buttons[2][i]});
                        }
                        check(Board[0][0], Board[1][1], Board[2][2], p1, p2, new Button[]{buttons[0][0], buttons[1][1], buttons[2][2]});
                        check(Board[0][2], Board[1][1], Board[2][0], p1, p2, new Button[]{buttons[0][2], buttons[1][1], buttons[2][0]});

                        move++;
                    } else {
                        Toast.makeText(GameActivity.this, "Invalid move detected", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GameActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                }
                if (move == 9 && found == false) {
                    changeLayout(p1, p2); // change weight of linear layout
                    p1.setText("It's a TIE  \uD83D\uDE29");
                    p1.setTextColor(Color.parseColor("#ED6464"));
                    Toast.makeText(GameActivity.this, "It's a TIE", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // change button prop
    public void changeButtonProp (Button[] btn) {
        for (Button b : btn) {
            b.setBackgroundColor(Color.GREEN);
            b.setTextColor(Color.WHITE);
        }
    }

    // change layout
    public void changeLayout (TextView p1, TextView p2) {
        LinearLayout.LayoutParams param1 = (LinearLayout.LayoutParams) p1.getLayoutParams();
        LinearLayout.LayoutParams param2 = (LinearLayout.LayoutParams) p2.getLayoutParams();

        p1.setLayoutParams(param1);
        p2.setLayoutParams(param2);

        param1.weight = 1;
        param2.weight = 0;

        p1.setTextSize(32);
        p1.setTextColor(Color.BLACK);
        p2.setText("");

    }

    // method for checking
    public void check (String a, String b, String c, TextView p1, TextView p2, Button[] btn) {
        if (a!=null && b!=null && c!=null && a==b && b==c) {
            found = true;
            changeButtonProp(btn);

            String s1 = p1.getText().toString();
            String s2 = p2.getText().toString();

            changeLayout(p1, p2); // change weight of linear layout

            if (a=="O") {
                Toast.makeText(this, "Winner is " + s1, Toast.LENGTH_SHORT).show();

                p1.setText("Congratulations \uD83E\uDD73" +s1.substring(2));
            } else {
                Toast.makeText(this, "Winner is " + s2, Toast.LENGTH_SHORT).show();

                p1.setText("Congratulations \uD83E\uDD73 " +s2.substring(2));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button bt00 = (Button) findViewById(R.id.b00);
        Button bt01 = (Button) findViewById(R.id.b01);
        Button bt02 = (Button) findViewById(R.id.b02);
        Button bt10 = (Button) findViewById(R.id.b10);
        Button bt11 = (Button) findViewById(R.id.b11);
        Button bt12 = (Button) findViewById(R.id.b12);
        Button bt20 = (Button) findViewById(R.id.b20);
        Button bt21 = (Button) findViewById(R.id.b21);
        Button bt22 = (Button) findViewById(R.id.b22);

        Button quit = (Button) findViewById(R.id.quit);
        Button reset = (Button) findViewById(R.id.reset);

        // Array of Buttons
        Button[][] btn =  new Button[][]{
            {bt00, bt01, bt02},
            {bt10, bt11, bt12},
            {bt20, bt21, bt22}
        };

        setButtons(btn);

        TextView p1 = (TextView) findViewById(R.id.p1);
        TextView p2 = (TextView) findViewById(R.id.p2);

        EditText e0 = (EditText) findViewById(R.id.etO);
        EditText eX = (EditText) findViewById(R.id.etX);

        Intent intent = getIntent();

        String s1 = intent.getStringExtra("p1");
        String s2 = intent.getStringExtra("p2");

        p1.setText("O: " + s1);
        p2.setText("X: " + s2);

        onChangeState(bt00, 0, 0, p1, p2);
        onChangeState(bt01, 0, 1, p1, p2);
        onChangeState(bt02, 0, 2, p1, p2);
        onChangeState(bt10, 1, 0, p1, p2);
        onChangeState(bt11, 1, 1, p1, p2);
        onChangeState(bt12, 1, 2, p1, p2);
        onChangeState(bt20, 2, 0, p1, p2);
        onChangeState(bt21, 2, 1, p1, p2);
        onChangeState(bt22, 2, 2, p1, p2);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recent = getIntent();
                finish();
                startActivity(recent);
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
