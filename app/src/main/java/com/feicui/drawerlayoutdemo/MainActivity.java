package com.feicui.drawerlayoutdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar= (Toolbar) findViewById(R.id.toolBar);
        navigationView= (NavigationView) findViewById(R.id.nav);
        //注意：将ToolBar设置到ActionBar位置时，需将Style里的ActionBar取消掉，否则会冲突报错
        setSupportActionBar(toolbar);//将toolBar设置到ActionBar位置
        getSupportActionBar().setTitle("ToolBar");

        toolbar.canShowOverflowMenu();
        //将draswerLayout与ToolBar设置同步,设置后ToolBar左侧会出现一个与DrawerLayout同步操作的开关
        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.app_name,R.string.app_name
        );
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(this);
    }
/**要实现ToolBar右侧开关，必须重写onCreateOptionsMenu方法，后面要return true*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_right,menu);
        return true;
    }
/**ToolBar右侧开关中的点击事件必须重写onOptionsItemSelected方法*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login_out:

                break;
            case R.id.exit:
                //点击退出按钮，弹出确认窗口Dialog，设置监听操作
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);//这里参数列表中的Context必须是Activity.this,否则主题不兼容报错
                builder.setIcon(R.drawable.ic_stars_black_24dp);
                builder.setMessage("真的要离开了吗");
                builder.setTitle("系统提示");
                builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("再看看", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                builder.create();//注意需要创建出dialog
                builder.show();//最后需要显示dialog
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
        super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String str="";
        switch (item.getItemId()){
            case R.id.item1:
                str="点击了第一个item";
                break;
            case R.id.item2:
                str="点击了第二个item";
                break;
            case R.id.item3:
                str="点击了第三个item";
                break;
            case R.id.item4:
                str="点击了第四个item";
                break;
            case R.id.item5:
                str="点击了第五个item";
                break;
        }
        Snackbar.make(navigationView,str,Snackbar.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
