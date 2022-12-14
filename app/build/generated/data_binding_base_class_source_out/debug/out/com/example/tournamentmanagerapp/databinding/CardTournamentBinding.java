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

public final class CardTournamentBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView imageIcon;

  @NonNull
  public final ConstraintLayout relativeLayout;

  @NonNull
  public final TextView tournamentMaxTeams;

  @NonNull
  public final TextView tournamentOrg;

  @NonNull
  public final TextView tournamentStartDate;

  @NonNull
  public final TextView tournamentTitle;

  private CardTournamentBinding(@NonNull CardView rootView, @NonNull ImageView imageIcon,
      @NonNull ConstraintLayout relativeLayout, @NonNull TextView tournamentMaxTeams,
      @NonNull TextView tournamentOrg, @NonNull TextView tournamentStartDate,
      @NonNull TextView tournamentTitle) {
    this.rootView = rootView;
    this.imageIcon = imageIcon;
    this.relativeLayout = relativeLayout;
    this.tournamentMaxTeams = tournamentMaxTeams;
    this.tournamentOrg = tournamentOrg;
    this.tournamentStartDate = tournamentStartDate;
    this.tournamentTitle = tournamentTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static CardTournamentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CardTournamentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.card_tournament, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CardTournamentBinding bind(@NonNull View rootView) {
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

      id = R.id.tournamentMaxTeams;
      TextView tournamentMaxTeams = ViewBindings.findChildViewById(rootView, id);
      if (tournamentMaxTeams == null) {
        break missingId;
      }

      id = R.id.tournamentOrg;
      TextView tournamentOrg = ViewBindings.findChildViewById(rootView, id);
      if (tournamentOrg == null) {
        break missingId;
      }

      id = R.id.tournamentStartDate;
      TextView tournamentStartDate = ViewBindings.findChildViewById(rootView, id);
      if (tournamentStartDate == null) {
        break missingId;
      }

      id = R.id.tournamentTitle;
      TextView tournamentTitle = ViewBindings.findChildViewById(rootView, id);
      if (tournamentTitle == null) {
        break missingId;
      }

      return new CardTournamentBinding((CardView) rootView, imageIcon, relativeLayout,
          tournamentMaxTeams, tournamentOrg, tournamentStartDate, tournamentTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
