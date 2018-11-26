package edu.gatech.ecotourism.activities;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.zhihu.matisse.Matisse;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import edu.gatech.ecotourism.Contact;
import edu.gatech.ecotourism.Listing;
import edu.gatech.ecotourism.R;
import edu.gatech.ecotourism.fragments.ContactsFragment;
import edu.gatech.ecotourism.fragments.ListingsFragment;
import edu.gatech.ecotourism.fragments.ShareMediaFragment;
import edu.gatech.ecotourism.fragments.TipsFragment;

public class MainActivity extends AppCompatActivity implements
        ListingsFragment.OnListingsFragmentInteractionListener,
        ContactsFragment.OnContactsFragmentInteractionListener,
        ShareMediaFragment.OnFragmentInteractionListener,
        TipsFragment.OnListFragmentInteractionListener {

    private static final int REQUEST_CODE_CHOOSE = 9001;
    Listing listing;
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
                            case 2:
                                clickListingsFragment();
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

    private void clickListingsFragment() {
        Fragment f = new ListingsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
        toolbar.setTitle(R.string.listings);
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
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        String rationale = "Please provide storage permission so that you can get images to share.";
        Permissions.Options options = new Permissions.Options().setRationaleDialogTitle("Info")
                .setSettingsDialogTitle("Warning");

        Permissions.check(this/*context*/, permissions, rationale, options, new PermissionHandler() {
            @Override
            public void onGranted() {
                Fragment f = new ShareMediaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
                toolbar.setTitle(R.string.share_media);
            }
        });
    }

    @Override
    public void onContactsFragmentInteraction(Contact item) {

    }

    @Override
    public void onListingsFragmentInteraction(Listing item) {
        Intent intent;
        if (item.getType().contentEquals(getString(R.string.house))) {
            intent = new Intent(this, HouseActivity.class);
        } else {
            intent = new Intent(this, ExperienceActivity.class);
        }
        intent.putExtra("listing", item);
        startActivity(intent);
    }

    @Override
    public void onShareMediaFragmentInteraction(String instagram) {

    }

    @Override
    public void onTipsListFragmentInteraction(String item) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", mSelected.toString());
        }
    }
}
