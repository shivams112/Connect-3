package io.github.shivams112.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     int activePlayer = 1; // 0:cross , 1:zero
       int gameState[] = {2,2,2,2,2,2,2,2,2}; //2 : empty state;
      int winningPosition[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    String winner =  "";
    int draw =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.animate().rotation(360).setDuration(300);

    }

    public void tapped(View view){

        ImageView imageView = (ImageView) view;
        int counter = Integer.parseInt(imageView.getTag().toString());
        if(draw==1) {
            Button button = findViewById(R.id.button);
            TextView textView = findViewById(R.id.textView);
            textView.setText("Draw");
            textView.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            draw=0;
        }
          if(gameState[counter]==2 && gameActive){
              gameState[counter] = activePlayer;
        if(activePlayer==1){

            imageView.setImageResource(R.drawable.zero);
            activePlayer=0;
        }
        else{
            imageView.setImageResource(R.drawable.cross);
            activePlayer =1;
        }

              for(int i =0; i<gameState.length;i++){
                  if(gameState[i]==2) {
                      draw = 0;
                      break;
                  }
                  else{
                      draw = 1 ;
                  }
              }


        for(int winning[] : winningPosition){

            if(gameState[winning[0]]==gameState[winning[1]] && gameState[winning[1]]==gameState[winning[2]] && gameState[winning[0]]!=2) {

                //someone has won
                if (activePlayer == 1) {
                    winner = "Cross";
                } else {
                    winner = "Zero";
                }
                gameActive = false;


                Button button = findViewById(R.id.button);
                TextView textView = findViewById(R.id.textView);

                textView.setText(winner + " has won");
                textView.setVisibility(View.VISIBLE);
               // button.animate().translationYBy(-100).setDuration(100);
                button.setVisibility(View.VISIBLE);
                draw = 0;
            }

          else{

                for(int i =0; i<gameState.length;i++){
                    if(gameState[i]==2) {
                        draw = 0;
                        break;
                    }
                    else{
                        draw = 1 ;
                    }
                }
            }

            }


        }

    }





    public void playagain(View view){

        Log.d("Connect 3:","play Tapped");
        Button button = findViewById(R.id.button);
        TextView textView =findViewById(R.id.textView);
        //button.animate().translationYBy(200).setDuration(100);

        textView.setVisibility(View.INVISIBLE);
       button.setVisibility(View.INVISIBLE);

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.animate().rotation(360).setDuration(300);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
        for(int i=0 ;i<gameState.length;i++){
            gameState[i]=2;
        }
        activePlayer =1;
        gameActive = true;
    }

}
