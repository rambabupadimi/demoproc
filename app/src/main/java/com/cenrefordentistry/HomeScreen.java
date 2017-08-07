package com.cenrefordentistry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cenrefordentistry.activities.AppointmentCalenderView;
import com.cenrefordentistry.fragments.Appointments;
import com.cenrefordentistry.fragments.Home;
import com.cenrefordentistry.fragments.Messages;
import com.cenrefordentistry.fragments.MyPlans;
import com.cenrefordentistry.fragments.MyPractice;
import com.cenrefordentistry.fragments.ReferFriend;
import com.cenrefordentistry.fragments.RequestCallBack;
import com.cenrefordentistry.fragments.TreatmentInfo;
import com.cenrefordentistry.fragments.VoucherWallet;
import com.cenrefordentistry.helper.CircleTransform;


public class HomeScreen extends AppCompatActivity {
        private NavigationView navigationView;
        private DrawerLayout drawer;
        private View navHeader;
        private ImageView imgNavHeaderBg, imgProfile;
        private TextView txtName, txtWebsite;
        private Toolbar toolbar;

        // urls to load navigation header background image
// and profile image

        // index to identify current nav menu item
        public static int navItemIndex = 0;

        // tags used to attach the fragments
        private static final String TAG_HOME = "Home";
        private static final String TAG_APPOINTEMNTS = "Appointments";
        private static final String TAG_MESSAGES = "Messages";
        private static final String TAG_MYPLANS = "MyPlans";
        private static final String TAG_MYPRACTICE = "MyPractice";
        private static final String TAG_REFERFRIEND = "ReferFriend";
        private static final String TAG_TREATMENTINFO = "TreatmentInfo";
        private static final String TAG_VOUCHERWALLET = "VoucherWallet";

        private static final String TAG_REQUEST_CALL_BACK ="RequestCallBack";

        public static String CURRENT_TAG = TAG_HOME;

        // toolbar titles respected to selected nav menu item
        private String[] activityTitles;

        // flag to load home fragment when user presses back key
        private boolean shouldLoadHomeFragOnBackPress = true;
        private Handler mHandler;


        TextView toolbarTitle;
        ImageView toolbarImage,toolbarSettings,toolbarRefresh,toolbarHome;


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_home_screen);
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                mHandler = new Handler();


                toolbarTitle = (TextView) findViewById(R.id.toolbar_heading);
                toolbarImage = (ImageView)findViewById(R.id.toolbar_icon);
                toolbarSettings = (ImageView)findViewById(R.id.toobar_settings);
                drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                navigationView = (NavigationView) findViewById(R.id.nav_view);

                toolbarRefresh  =       (ImageView)findViewById(R.id.toobar_refresh);
                toolbarHome     =       (ImageView)findViewById(R.id.toolbar_home);


                toolbarHome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                loadHomeFragment(AppConstants.TAG_HOME,AppConstants.HOME_INDEX);

                        }
                });


                // Navigation view header
 /*
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);
*/
                // load toolbar titles from string resources
                activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);


                // load nav menu header data
                loadNavHeader();

                // initializing navigation menu
                setUpNavigationView();

                try
                {
                        if(getIntent().getStringExtra("from")!=null) {
                                if (getIntent().getStringExtra("from").toString().equalsIgnoreCase("not_nav")) {
                                        try {
                                                int index = getIntent().getIntExtra("index", 0);
                                                String tag = getIntent().getStringExtra("tag");
                                                loadHomeFragment(tag, index);

                                        } catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                        else
                        {
                                if (savedInstanceState == null) {
                                        navItemIndex = 0;
                                        CURRENT_TAG = TAG_HOME;
                                        loadHomeFragment(CURRENT_TAG,navItemIndex);
                                }

                        }
                }catch (Exception e)
                {
                        e.printStackTrace();
                }
        }

        /***
         * Load navigation menu header information
         * like background image, profile image
         * name, website, notifications action view (dot)
         */
        private void loadNavHeader() {
                navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDarker)));
                navigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDarker)));

        }

        /***
         * Returns respected fragment that user
         * selected from navigation menu
         */
        public void loadHomeFragment(final String tag,int index) {
                // selecting appropriate nav menu item

                navItemIndex = index;
                selectNavMenu();
                // set toolbar title
                setToolbarTitle();

                // if user select the current navigation menu again, don't do anything
                // just close the navigation drawer
                if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
                        drawer.closeDrawers();

                        // show or hide the fab button
                        //toggleFab();
                        return;
                }

                // Sometimes, when fragment has huge data, screen seems hanging
                // when switching between navigation menus
                // So using runnable, the fragment is loaded with cross fade effect
                // This effect can be seen in GMail app
                Runnable mPendingRunnable = new Runnable() {
                        @Override
                        public void run() {
                                // update the main content by replacing fragments
                                Fragment fragment = getHomeFragment();
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                                        android.R.anim.fade_out);
                                fragmentTransaction.replace(R.id.frame, fragment, tag);
                                fragmentTransaction.commitAllowingStateLoss();
                        }
                };

                // If mPendingRunnable is not null, then add to the message queue
                if (mPendingRunnable != null) {
                        if(mHandler!=null)
                        mHandler.post(mPendingRunnable);
                }

                // show or hide the fab button
                //toggleFab();

                //Closing drawer on item click
                if(drawer!=null)
                drawer.closeDrawers();
               // navigationView.getMenu().getItem(index).setActionView(R.layout.menu_dot);


                // refresh toolbar menu
                invalidateOptionsMenu();
        }

        @SuppressLint("ResourceAsColor")
        private Fragment getHomeFragment() {
                switch (navItemIndex) {
                        case 0:
                                // home
                                Home homeFragment = new Home();

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        Window window = getWindow();
                                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        window.setStatusBarColor(Color.parseColor("#007C32"));
                                }
                                toolbarTitle.setTextColor(Color.parseColor("#007C32"));
                                toolbarSettings.setColorFilter(Color.parseColor("#007C32"));
                                toolbarImage.setColorFilter(Color.parseColor("#007C32"));
                                toolbarTitle.setText(activityTitles[0].toString());
                                toolbarSettings.setVisibility(View.VISIBLE);
                                toolbarRefresh.setVisibility(View.GONE);
                                toolbarHome.setVisibility(View.GONE);
                                toolbarHome.setColorFilter(Color.parseColor("#007C32"));
                                Glide.with(this).load(R.drawable.ic_home_black_48dp).into(toolbarImage);
                                return homeFragment;
                        case 1:
                                // photos

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        Window window = getWindow();
                                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        window.setStatusBarColor(Color.parseColor("#007C32"));
                                }
                                MyPractice myPractice = new MyPractice();
                                toolbarTitle.setTextColor(Color.parseColor("#007C32"));
                                toolbarSettings.setColorFilter(Color.parseColor("#007C32"));
                                toolbarImage.setColorFilter(Color.parseColor("#007C32"));
                                toolbarTitle.setText(activityTitles[1].toString());
                                Glide.with(this).load(R.drawable.ic_cfd_notification).into(toolbarImage);
                                toolbarSettings.setVisibility(View.GONE);
                                toolbarRefresh.setVisibility(View.GONE);
                                toolbarHome.setVisibility(View.VISIBLE);
                                toolbarHome.setColorFilter(Color.parseColor("#007C32"));

                                return myPractice;
                        case 2:
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        Window window = getWindow();
                                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        window.setStatusBarColor(Color.parseColor("#007C32"));
                                }
                                Appointments appointments = new Appointments();
                                toolbarTitle.setTextColor(Color.parseColor("#007C32"));
                                toolbarSettings.setColorFilter(Color.parseColor("#007C32"));
                                toolbarImage.setColorFilter(Color.parseColor("#007C32"));
                                toolbarTitle.setText(activityTitles[2].toString());
                                Glide.with(this).load(R.drawable.ic_event_white_24dp).into(toolbarImage);
                                toolbarSettings.setVisibility(View.GONE);
                                toolbarRefresh.setVisibility(View.GONE);
                                toolbarHome.setVisibility(View.VISIBLE);
                                toolbarHome.setColorFilter(Color.parseColor("#007C32"));

                                return appointments;

                        case 3:

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        Window window = getWindow();
                                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        window.setStatusBarColor(Color.parseColor("#007C32"));
                                }
                                Messages messages = new Messages();
                                toolbarTitle.setTextColor(Color.parseColor("#007C32"));
                                toolbarSettings.setColorFilter(Color.parseColor("#007C32"));
                                toolbarImage.setColorFilter(Color.parseColor("#007C32"));
                                toolbarTitle.setText(activityTitles[3].toString());
                                Glide.with(this).load(R.drawable.ic_mail_white_24dp).into(toolbarImage);
                                toolbarSettings.setVisibility(View.GONE);
                                toolbarRefresh.setVisibility(View.VISIBLE);
                                toolbarHome.setVisibility(View.VISIBLE);
                                toolbarHome.setColorFilter(Color.parseColor("#007C32"));
                                toolbarRefresh.setColorFilter(Color.parseColor("#007C32"));

                                return messages;
                        case 4:
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        Window window = getWindow();
                                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        window.setStatusBarColor(Color.parseColor("#C70973"));
                                }
                                VoucherWallet voucherWallet = new VoucherWallet();
                                toolbarTitle.setTextColor(Color.parseColor("#C70973"));
                                toolbarSettings.setColorFilter(Color.parseColor("#c70973"));
                                toolbarImage.setColorFilter(Color.parseColor("#c70973"));
                                toolbarTitle.setText(activityTitles[4].toString());

                                Glide.with(this).load(R.drawable.voucher).into(toolbarImage);
                                toolbarSettings.setVisibility(View.GONE);
                                toolbarRefresh.setVisibility(View.VISIBLE);
                                toolbarHome.setVisibility(View.VISIBLE);
                                toolbarHome.setColorFilter(Color.parseColor("#c70973"));
                                toolbarRefresh.setColorFilter(Color.parseColor("#c70973"));

                                return voucherWallet;

                        case 5:

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        Window window = getWindow();
                                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        window.setStatusBarColor(Color.parseColor("#8BC34A"));
                                }
                                TreatmentInfo treatmentInfo = new TreatmentInfo();
                                toolbarTitle.setTextColor(Color.parseColor("#8BC34A"));
                                toolbarSettings.setColorFilter(Color.parseColor("#8BC34A"));
                                toolbarImage.setColorFilter(Color.parseColor("#8BC34A"));
                                toolbarTitle.setText(activityTitles[5].toString());
                                Glide.with(this).load(R.drawable.ic_local_pharmacy_white_24dp).into(toolbarImage);
                                toolbarHome.setVisibility(View.VISIBLE);
                                toolbarSettings.setVisibility(View.GONE);
                                toolbarRefresh.setVisibility(View.GONE);
                                toolbarHome.setColorFilter(Color.parseColor("#8BC34A"));

                                return treatmentInfo;

                        case 6:
                                // settings fragment

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        Window window = getWindow();
                                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        window.setStatusBarColor(Color.parseColor("#15A3DD"));
                                }

                                MyPlans myPlans = new MyPlans();
                                toolbarTitle.setTextColor(Color.parseColor("#15A3DD"));
                                toolbarSettings.setColorFilter(Color.parseColor("#15A3DD"));
                                toolbarImage.setColorFilter(Color.parseColor("#15A3DD"));
                                Glide.with(this).load(R.drawable.plan).into(toolbarImage);
                                toolbarTitle.setText(activityTitles[6].toString());
                                toolbarHome.setVisibility(View.VISIBLE);
                                toolbarSettings.setVisibility(View.GONE);
                                toolbarRefresh.setVisibility(View.GONE);
                                toolbarHome.setColorFilter(Color.parseColor("#15A3DD"));

                                return myPlans;

                        case 7:
                                // settings fragment
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        Window window = getWindow();
                                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        window.setStatusBarColor(Color.parseColor("#007C32"));
                                }


                                ReferFriend referFriend = new ReferFriend();
                                toolbarTitle.setTextColor(Color.parseColor("#007C32"));
                                toolbarSettings.setColorFilter(Color.parseColor("#007C32"));
                                toolbarImage.setColorFilter(Color.parseColor("#007C32"));
                                Glide.with(this).load(R.drawable.ic_local_offer_white_24dp).into(toolbarImage);

                                toolbarSettings.setVisibility(View.GONE);
                                toolbarRefresh.setVisibility(View.GONE);
                                toolbarHome.setVisibility(View.GONE);

                                toolbarTitle.setText(activityTitles[7].toString());
                                return referFriend;



                        case 8:
                                // settings fragment
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        Window window = getWindow();
                                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        window.setStatusBarColor(Color.parseColor("#007C32"));
                                }

                                RequestCallBack requestCallBack = new RequestCallBack();
                                toolbarTitle.setTextColor(Color.parseColor("#007C32"));
                                toolbarSettings.setColorFilter(Color.parseColor("#007C32"));
                                toolbarImage.setColorFilter(Color.parseColor("#ffffff"));
                              //  Glide.with(this).load(R.drawable.ic_local_offer_white_24dp).into(toolbarImage);

                                toolbarSettings.setVisibility(View.GONE);
                                toolbarRefresh.setVisibility(View.GONE);
                                toolbarHome.setVisibility(View.VISIBLE);
                                toolbarTitle.setText(activityTitles[8].toString());
                                return requestCallBack;


                        default:
                                return new Home();
                }
        }

        private void setToolbarTitle() {
              //  getSupportActionBar().setTitle(activityTitles[navItemIndex]);

        }

        private void selectNavMenu() {
                if(navigationView!=null)
                        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
        }

        private void setUpNavigationView() {
                //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
                navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                        // This method will trigger on item Click of navigation menu
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {

                                //Check to see which item was being clicked and perform appropriate action
                                switch (menuItem.getItemId()) {
                                        //Replacing the main content with ContentFragment Which is our Inbox View;
                                        case R.id.nav_home:
                                                navItemIndex = 0;
                                                CURRENT_TAG = TAG_HOME;
                                                break;
                                        case R.id.nav_my_practice:
                                                navItemIndex = 1;
                                                CURRENT_TAG = TAG_MYPRACTICE;
                                                break;
                                        case R.id.nav_appointments:
                                                navItemIndex = 2;
                                                CURRENT_TAG = TAG_APPOINTEMNTS;
                                                break;
                                        case R.id.nav_messages:
                                                navItemIndex = 3;
                                                CURRENT_TAG = TAG_MESSAGES;
                                                break;
                                        case R.id.nav_voucher_wallet:
                                                navItemIndex = 4;
                                                CURRENT_TAG = TAG_VOUCHERWALLET;
                                                break;
                                        case R.id.nav_treatment_info:
                                                navItemIndex = 5;
                                                CURRENT_TAG = TAG_TREATMENTINFO;
                                                break;
                                        case R.id.nav_my_plans:
                                                navItemIndex = 6;
                                                CURRENT_TAG = TAG_MYPLANS;
                                                break;
                                        case R.id.nav_refer_friend:
                                                navItemIndex = 7;
                                                CURRENT_TAG = TAG_REFERFRIEND;
                                                break;
                                        case R.id.nav_request_callback:
                                                navItemIndex = 8;
                                                CURRENT_TAG = TAG_REQUEST_CALL_BACK;
                                                break;

 /*
        case R.id.nav_about_us:
        // launch new intent instead of loading fragment
        startActivity(new Intent(HomeScreen.this, AboutUsActivity.class));
        drawer.closeDrawers();
        return true;
        case R.id.nav_privacy_policy:
        // launch new intent instead of loading fragment
        startActivity(new Intent(HomeScreen.this, PrivacyPolicyActivity.class));
        drawer.closeDrawers();
        return true;
 */

                                        default:
                                                navItemIndex = 0;
                                }

                                //Checking if the item is in checked state or not, if not make it in checked state
                                if (menuItem.isChecked()) {
                                        menuItem.setChecked(false);
                                } else {
                                        menuItem.setChecked(true);
                                }
                                menuItem.setChecked(true);

                                loadHomeFragment(CURRENT_TAG,navItemIndex);

                                return true;
                        }
                });


                ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

                        @Override
                        public void onDrawerClosed(View drawerView) {
                                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                                super.onDrawerClosed(drawerView);
                        }

                        @Override
                        public void onDrawerOpened(View drawerView) {
                                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                                super.onDrawerOpened(drawerView);
                        }
                };

                //Setting the actionbarToggle to drawer layout
                drawer.setDrawerListener(actionBarDrawerToggle);

                //calling sync state is necessary or else your hamburger icon wont show up
                actionBarDrawerToggle.syncState();
        }
        private Boolean exit = false;
        @Override
        public void onBackPressed() {
                if (exit) {
                        finish(); // finish activity
                } else {
                        Toast.makeText(this, "Press Back again to Exit.",
                                Toast.LENGTH_SHORT).show();
                        exit = true;
                        new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                        exit = false;
                                }
                        }, 3 * 1000);

                }

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawers();
                        return;
                }

                // This code loads home fragment when back key is pressed
                // when user is in other fragment than home
                if (shouldLoadHomeFragOnBackPress) {
                        // checking if user is on other navigation menu
                        // rather than home
                        if (navItemIndex != 0) {
                                navItemIndex = 0;
                                CURRENT_TAG = TAG_HOME;
                                loadHomeFragment(CURRENT_TAG,navItemIndex);
                                return;
                        }
                }

                super.onBackPressed();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.

              //    getMenuInflater().inflate(R.menu.main, menu);

                // show menu only when home fragment is selected
                if (navItemIndex == 0) {
                      //  getMenuInflater().inflate(R.menu.main, menu);
                }

                // when fragment is notifications, load the menu created for notifications
                if (navItemIndex == 3) {
                      //  getMenuInflater().inflate(R.menu.notifications, menu);
                }
                return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.settings) {
                   //     showOptionMenu();
                       // Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
                        return true;
                }

                // user is in notifications fragment
                // and selected 'Mark all as Read'
                if (id == R.id.action_mark_all_read) {
                        Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
                }

                // user is in notifications fragment
                // and selected 'Clear All'
                if (id == R.id.action_clear_notifications) {
                        Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
                }

                return super.onOptionsItemSelected(item);
        }

        // show or hide the fab

        /**
         private void toggleFab() {
         if (navItemIndex == 0)
         fab.show();
         else
         fab.hide();
         }

         **/



}