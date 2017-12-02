package ec.edu.uisrael.ventas.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ec.edu.uisrael.ventas.R;
import ec.edu.uisrael.ventas.controlador.ControllerLogin;
import ec.edu.uisrael.ventas.controlador.ControllerViews;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private ControllerLogin adminLogin;
    private ControllerViews adminview;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPassword = (EditText) findViewById(R.id.txtpassword);
        etUsername = (EditText) findViewById(R.id.txtusername);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        adminLogin = new ControllerLogin();
        adminview = ControllerViews.getInstance();

    }

    public void onclicklogin (View v) {
        String user = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (adminLogin.login(user,password)) {
            Intent loginok = ControllerViews.getViewlogin(MainActivity.this, VehiculoList.class);
            startActivity(loginok);
        }else {
            Toast.makeText(MainActivity.this, getString(R.string.error_incorrect_login), Toast.LENGTH_SHORT).show();
        }

    }

}
