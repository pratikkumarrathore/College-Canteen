package my.collegecanteen.app.OwnerFoodPanel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import my.collegecanteen.app.R;

public class OwnerProfileFragment extends Fragment {

    ImageView bgimage5;
    Button post;
    @SuppressLint("MissingInflatedId")

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_owner_profile, null);
        getActivity().setTitle("Post dish");

        //Animation code
        final Animation zoomin = AnimationUtils.loadAnimation(getContext(), R.anim.zoomin);
        final Animation zoomout = AnimationUtils.loadAnimation(getContext(), R.anim.zoomout);
        bgimage5 = v.findViewById(R.id.foodheart);
        bgimage5.setAnimation(zoomin);
        bgimage5.setAnimation(zoomout);

        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgimage5.startAnimation(zoomin);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        zoomin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgimage5.startAnimation(zoomout);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        post = (Button) v.findViewById(R.id.post_dish);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OwnerPostDish.class));
            }
        });
        return v;
    }

}
