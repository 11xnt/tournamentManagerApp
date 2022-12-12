// Generated by view binder compiler. Do not edit!
package com.example.tournamentmanagerapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.tournamentmanagerapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CardTeamBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView imageIcon;

  @NonNull
  public final ConstraintLayout relativeLayout;

  @NonNull
  public final TextView teamName;

  private CardTeamBinding(@NonNull CardView rootView, @NonNull ImageView imageIcon,
      @NonNull ConstraintLayout relativeLayout, @NonNull TextView teamName) {
    this.rootView = rootView;
    this.imageIcon = imageIcon;
    this.relativeLayout = relativeLayout;
    this.teamName = teamName;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static CardTeamBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CardTeamBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.card_team, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CardTeamBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageIcon;
      ImageView imageIcon = ViewBindings.findChildViewById(rootView, id);
      if (imageIcon == null) {
        break missingId;
      }

      id = R.id.relativeLayout;
      ConstraintLayout relativeLayout = ViewBindings.findChildViewById(rootView, id);
      if (relativeLayout == null) {
        break missingId;
      }

      id = R.id.teamName;
      TextView teamName = ViewBindings.findChildViewById(rootView, id);
      if (teamName == null) {
        break missingId;
      }

      return new CardTeamBinding((CardView) rootView, imageIcon, relativeLayout, teamName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}