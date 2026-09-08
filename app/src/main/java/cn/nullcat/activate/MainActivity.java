package cn.nullcat.activate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean isActive;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        updateButtonLabel();

        btn.setOnClickListener(v -> {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "请在“设置”中启用“显示在其他应用上层”权限", Toast.LENGTH_SHORT).show();
                Intent settingsIntent = new Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName())
                );
                startActivity(settingsIntent);
                return;
            }

            Intent intent = new Intent(this, PopupService.class);
            if (!isActive) {
                startService(intent);
                Toast.makeText(this, "反向破解成功", Toast.LENGTH_SHORT).show();
                isActive = true;
            } else {
                stopService(intent);
                Toast.makeText(this, "正版已恢复（暂时）", Toast.LENGTH_SHORT).show();
                isActive = false;
            }
            updateButtonLabel();
        });
    }

    private void updateButtonLabel() {
        if (btn == null) {
            return;
        }
        btn.setText(isActive ? "恢复正版" : getString(R.string.btn_text));
    }
}
