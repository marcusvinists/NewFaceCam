package sistemaintegrado.facecam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;

public class NewFaceCam extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_face_cam);
        Button button1 = (Button)findViewById(R.id.button1);
        //seta a ação ao clicar no botão
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                iniciaCamera();
            }
        });
    }
    //inicia o aplicativo nativo da camera
    public void iniciaCamera(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //Verifica se a foto foi tirada
        if(resultCode== RESULT_OK) {
            //Armazena a foto e a converte em um array de bytes para ser enviada via Intent para o visualizador
            Bitmap foto = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Intent intent = new Intent(NewFaceCam.this, Visualizador.class);
            intent.putExtra("picture", byteArray);
            startActivity(intent);
            setContentView(R.layout.activity_visualizador);
        } else {
            //cancelou a foto
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_face_cam, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}