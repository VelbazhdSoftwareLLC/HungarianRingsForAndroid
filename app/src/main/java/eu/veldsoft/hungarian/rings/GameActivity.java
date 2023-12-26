package eu.veldsoft.hungarian.rings;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private static int FORMULA_REQUEST_ID = 1;

    private Rings rings = null;

    private ImageView views[] = {};

    MediaPlayer beep02Player = null;

    MediaPlayer hit01Player = null;

    private MediaPlayer cartoon007Plauyer = null;

    private MediaPlayer cartoon012Player = null;

    private View.OnClickListener aRingClockwiseClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            rings.ccwa();
            GameActivity.this.updateInfo();
        }
    };

    private View.OnClickListener aRingCounterClockwiseClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            rings.cwa();
            GameActivity.this.updateInfo();
        }
    };

    private View.OnClickListener bRingClockwiseClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            rings.ccwb();
            GameActivity.this.updateInfo();
        }
    };

    private View.OnClickListener bRingCounterClockwiseClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            rings.cwb();
            GameActivity.this.updateInfo();
        }
    };

    private void updateInfo() {
        sound();
        repaint();
        congratulate();
    }

    private void congratulate() {
        if (rings.isDone() == true) {
            Toast.makeText(this, R.string.you_win_message, Toast.LENGTH_LONG).show();
        }
    }

    private void sound() {
        if (rings.isDone() == true) {
            beep02Player.start();
        } else {
            hit01Player.start();
        }
    }

    private void repaint() {
        int state[] = rings.getState();

        if (views.length != state.length) {
            return;
        }

        for (int i = 0; i < views.length; i++) {
            switch (state[i]) {
                case 1:
                    views[i].setImageResource(R.drawable.blue);
                    break;
                case 2:
                    views[i].setImageResource(R.drawable.red);
                    break;
                case 3:
                    views[i].setImageResource(R.drawable.green);
                    break;
                case 4:
                    views[i].setImageResource(R.drawable.violet);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        beep02Player = MediaPlayer.create(this, R.raw.beep_02);
        hit01Player = MediaPlayer.create(this, R.raw.hit_01);
        cartoon007Plauyer = MediaPlayer.create(this, R.raw.cartoon007);
        cartoon012Player = MediaPlayer.create(this, R.raw.cartoon012);

        ImageView views[] = {(ImageView) findViewById(R.id.imageView000),
                (ImageView) findViewById(R.id.imageView001),
                (ImageView) findViewById(R.id.imageView002),
                (ImageView) findViewById(R.id.imageView003),
                (ImageView) findViewById(R.id.imageView004),
                (ImageView) findViewById(R.id.imageView005),
                (ImageView) findViewById(R.id.imageView006),
                (ImageView) findViewById(R.id.imageView007),
                (ImageView) findViewById(R.id.imageView008),
                (ImageView) findViewById(R.id.imageView009),
                (ImageView) findViewById(R.id.imageView010),
                (ImageView) findViewById(R.id.imageView011),
                (ImageView) findViewById(R.id.imageView012),
                (ImageView) findViewById(R.id.imageView013),
                (ImageView) findViewById(R.id.imageView014),
                (ImageView) findViewById(R.id.imageView015),
                (ImageView) findViewById(R.id.imageView016),
                (ImageView) findViewById(R.id.imageView017),
                (ImageView) findViewById(R.id.imageView100),
                (ImageView) findViewById(R.id.imageView101),
                (ImageView) findViewById(R.id.imageView102),
                (ImageView) findViewById(R.id.imageView103),
                (ImageView) findViewById(R.id.imageView104),
                (ImageView) findViewById(R.id.imageView105),
                (ImageView) findViewById(R.id.imageView106),
                (ImageView) findViewById(R.id.imageView107),
                (ImageView) findViewById(R.id.imageView108),
                (ImageView) findViewById(R.id.imageView109),
                (ImageView) findViewById(R.id.imageView110),
                (ImageView) findViewById(R.id.imageView111),
                (ImageView) findViewById(R.id.imageView112),
                (ImageView) findViewById(R.id.imageView113),
                (ImageView) findViewById(R.id.imageView114),
                (ImageView) findViewById(R.id.imageView115),
                (ImageView) findViewById(R.id.imageView116),
                (ImageView) findViewById(R.id.imageView117),};
        this.views = views;

        findViewById(R.id.arrow01).setOnClickListener(aRingClockwiseClick);
        findViewById(R.id.arrow02).setOnClickListener(
                aRingCounterClockwiseClick);
        findViewById(R.id.arrow03).setOnClickListener(
                bRingCounterClockwiseClick);
        findViewById(R.id.arrow04).setOnClickListener(bRingClockwiseClick);

        findViewById(R.id.imageView005).setOnClickListener(
                aRingCounterClockwiseClick);
        findViewById(R.id.imageView006).setOnClickListener(
                aRingCounterClockwiseClick);
        findViewById(R.id.imageView007).setOnClickListener(
                aRingCounterClockwiseClick);
        findViewById(R.id.imageView008).setOnClickListener(
                aRingCounterClockwiseClick);
        findViewById(R.id.imageView009).setOnClickListener(
                aRingCounterClockwiseClick);
        findViewById(R.id.imageView010).setOnClickListener(
                aRingCounterClockwiseClick);

        findViewById(R.id.imageView012).setOnClickListener(aRingClockwiseClick);
        findViewById(R.id.imageView013).setOnClickListener(aRingClockwiseClick);
        findViewById(R.id.imageView014).setOnClickListener(aRingClockwiseClick);
        findViewById(R.id.imageView015).setOnClickListener(aRingClockwiseClick);
        findViewById(R.id.imageView016).setOnClickListener(aRingClockwiseClick);
        findViewById(R.id.imageView017).setOnClickListener(aRingClockwiseClick);

        findViewById(R.id.imageView105).setOnClickListener(
                bRingCounterClockwiseClick);
        findViewById(R.id.imageView106).setOnClickListener(
                bRingCounterClockwiseClick);
        findViewById(R.id.imageView107).setOnClickListener(
                bRingCounterClockwiseClick);
        findViewById(R.id.imageView108).setOnClickListener(
                bRingCounterClockwiseClick);
        findViewById(R.id.imageView109).setOnClickListener(
                bRingCounterClockwiseClick);
        findViewById(R.id.imageView110).setOnClickListener(
                bRingCounterClockwiseClick);

        findViewById(R.id.imageView112).setOnClickListener(bRingClockwiseClick);
        findViewById(R.id.imageView113).setOnClickListener(bRingClockwiseClick);
        findViewById(R.id.imageView114).setOnClickListener(bRingClockwiseClick);
        findViewById(R.id.imageView115).setOnClickListener(bRingClockwiseClick);
        findViewById(R.id.imageView116).setOnClickListener(bRingClockwiseClick);
        findViewById(R.id.imageView117).setOnClickListener(bRingClockwiseClick);

        ((ImageView) findViewById(R.id.ebinqoLogo))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GameActivity.this.startActivity(new Intent(
                                Intent.ACTION_VIEW, Uri.parse(getResources()
                                .getString(R.string.ebinqo_url))));
                    }
                });

        rings = new Rings(this.getWindow().getDecorView().getWidth(), this
                .getWindow().getDecorView().getHeight());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == FORMULA_REQUEST_ID) {
            String formula = data.getCharSequenceExtra(
                    InstructionActivity.EXTRA_RESULT_FORMULA_KEY).toString();
            rings.eval(formula.toUpperCase());
            GameActivity.this.updateInfo();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.run_formula) {
            startActivityForResult(new Intent(GameActivity.this,
                    InstructionActivity.class), FORMULA_REQUEST_ID);
        }

        if (item.getItemId() == R.id.reset_game) {
            rings.init(0, 0);
            cartoon007Plauyer.start();
            GameActivity.this.repaint();
        }

        if (item.getItemId() == R.id.shuffle_game) {
            rings.shuffle();
            cartoon012Player.start();
            GameActivity.this.repaint();
        }

        if (item.getItemId() == R.id.help_game) {
            startActivity(new Intent(GameActivity.this, HelpActivity.class));

        }
        if (item.getItemId() == R.id.about_game) {
            startActivity(new Intent(GameActivity.this, AboutActivity.class));
        }

        return true;
    }
}
