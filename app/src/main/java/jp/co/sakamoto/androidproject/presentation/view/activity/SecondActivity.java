package jp.co.sakamoto.androidproject.presentation.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import jp.co.sakamoto.androidproject.R;
import jp.co.sakamoto.androidproject.databinding.ActivitySecondBinding;
import jp.co.sakamoto.androidproject.presentation.view.fragment.GalleryFragment;
import jp.co.sakamoto.androidproject.presentation.view.fragment.ToolsFragment;
import jp.co.sakamoto.androidproject.presentation.viewmodel.activity.SecondActivityViewModel;

public class SecondActivity extends BaseActivity implements HasSupportFragmentInjector {
    private static final String TAG = "SecondActivity";

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;
    @Inject
    SecondActivityViewModel viewModel;

    private ActivitySecondBinding binding;
    private ToolsFragment toolsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        this.subscriptions.add(viewModel);
        this.binding.setViewModel(viewModel);

        // Toolbar Setting
        Toolbar toolbar = (Toolbar) this.binding.toolbar;
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, this.binding.drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        this.binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        this.binding.navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch(menuItem.getItemId()){
                case R.id.menu_item1:
                    Log.d(TAG, "Item 1 Selected!");
                    break;
                case R.id.menu_item2:
                    Log.d(TAG, "Item 2 Selected!");
                    break;
                case R.id.menu_item3:
                    Log.d(TAG, "Item 3 Selected!");
                    break;
                case R.id.menu_item4:
                    Log.d(TAG, "Item 4 Selected!");
                    break;
            }
            this.binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        this.toolsFragment = ToolsFragment.newInstance();
        // BottomNavigation Setting
        this.binding.bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch(menuItem.getItemId()){
                case R.id.nav_camera:
                    replaceContainer(GalleryFragment.newInstance());
                    break;
                case R.id.nav_gallery:
                    replaceContainer(GalleryFragment.newInstance());
                    break;
                case R.id.nav_slideshow:
                    Log.d(TAG, "Item 3 Selected!");
                    break;
                case R.id.nav_manage:
                    if (this.binding.bottomNavigation.getSelectedItemId() == R.id.nav_manage) {
                        this.toolsFragment = ToolsFragment.newInstance();
                    }
                    replaceContainer(this.toolsFragment);
                    break;
            }
            return true;
        });
        replaceContainer(GalleryFragment.newInstance());
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return this.fragmentInjector;
    }

    private void replaceContainer(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
