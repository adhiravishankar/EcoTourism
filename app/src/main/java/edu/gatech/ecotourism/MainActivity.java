package edu.gatech.ecotourism;

import android.os.Bundle;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mooveit.library.Fakeit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements
        ListingsFragment.OnListFragmentInteractionListener,
        ContactsFragment.OnListFragmentInteractionListener,
        ShareMediaFragment.OnFragmentInteractionListener,
        TipsFragment.OnListFragmentInteractionListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fakeit.init();

        toolbar = findViewById(R.id.toolbar);

        PrimaryDrawerItem contacts = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.contacts);
        PrimaryDrawerItem listings = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.listings);
        PrimaryDrawerItem shareMedia = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.share_media);
        PrimaryDrawerItem tips = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.tips);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .addProfiles(
                        new ProfileDrawerItem().withName("Adhithya Ravishankar").
                                withEmail("adhiravishankar@gmail.com").
                                withIcon(getResources().getDrawable(R.drawable.profile, null))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //Now create your drawer and pass the AccountHeader.Result
        new DrawerBuilder().withActivity(this).withSavedInstance(savedInstanceState).
                withDisplayBelowStatusBar(true).withTranslucentStatusBar(true).
                withActionBarDrawerToggle(true).
                withToolbar(toolbar).
                addDrawerItems(contacts, listings, shareMedia, tips).
                withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                clickContactsFragment();
                                break;
                            case 3:
                                clickShareMediaFragment();
                                break;
                            case 4:
                                clickTipsFragment();
                                break;
                        }
                        return false;
                    }
                }).
                withAccountHeader(headerResult).build();
        //additional Drawer setup as shown above

        clickContactsFragment();
    }

    private void clickTipsFragment() {
        Fragment f = new TipsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
        toolbar.setTitle(R.string.tips);
    }

    private void clickContactsFragment() {
        Fragment f = new ContactsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
        toolbar.setTitle(R.string.contacts);
    }

    private void clickShareMediaFragment() {
        Fragment f = new ShareMediaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
        toolbar.setTitle(R.string.share_media);
    }

    @Override
    public void onListFragmentInteraction(Contact item) {

    }

    @Override
    public void onListFragmentInteraction(Listing item) {

    }

    @Override
    public void onShareMediaFragmentInteraction(String instagram) {

    }

    @Override
    public void onTipsListFragmentInteraction(String item) {

    }
}
