package com.example.simpleasynctask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

/*** L'application SimpleAsyncTask contient un bouton qui lance une AsyncTask
 * qui dort dans le thread asynchrone pendant une durée aléatoire.
 */
public class MainActivity extends AppCompatActivity {

    // Clé de sauvegarde de l'état du TextView
    private static final String TEXT_STATE = "currentText";

    // Le TextView où nous afficherons les résultatsprivate
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialise mTextView
        mTextView = findViewById(R.id.TextSleep);

        // Restaure TextView s'il y a un saveInstanceState
        if(savedInstanceState!=null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }

        Button button = findViewById(R.id.btnSleep); // Remplacez "button" par l'ID de votre bouton dans le fichier de mise en page (layout)

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int n = r.nextInt(11);
                int s = n * 1000;
                ProgressBar progressBar = findViewById(R.id.progressBar);
                for (int i = 0; i < 100; i++) {
                    startTask(v, s/100);
                    progressBar.setProgress(progressBar.getProgress() + 1, true);
                }
            }
        });


    }


    /*** Gère le onCLick pour le bouton "Démarrer la tâche".
     * Lance l'AsyncTask qui effectue un travail hors du thread UI.
     *
     * @param view La vue (bouton) qui a été cliquée.
     */
    public void startTask(View view, Integer number) {
        // Placer un message dans la vue texte
        mTextView.setText("Laisse moi dormir");
        // Démarre l'Async Task.
        String text = new SimpleAsyncTask(mTextView).doInBackground(number);
        mTextView.setText(text);
    }

    /*** Enregistre le contenu de TextView à restaurer lors d'un changement de configuration.
     * @param outState -> Le bundle dans lequel l'état de l'activité est enregistré
     * lorsqu'elle est détruite spontanément.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        // Enregistre l'état de TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }

}