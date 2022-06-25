package cn.nullcat.activate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AppOpsManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private boolean is_active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.button);
        Intent intent = new Intent(this, PopupService.class);
        btn.setOnClickListener(v -> {
            AppOpsManager opsMgr = (AppOpsManager) getSystemService(APP_OPS_SERVICE);
            if (opsMgr.checkOpNoThrow(
                    "android:system_alert_window", android.os.Process.myUid(), getPackageName()
            ) > 1) {
                Toast.makeText(this, "请在“设置”中启用“显示在其他应用上层”权限", Toast.LENGTH_SHORT).show();
            } else {
                if (!is_active) {
                    startService(intent);
                    Toast.makeText(this, "反向破解成功", Toast.LENGTH_SHORT).show();
                    is_active = true;
                } else Toast.makeText(this, "你现在已经是盗版了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}