package com.example.google_ad_mob;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class MainActivity extends AppCompatActivity {

    private FrameLayout adContainerView;
    private AdView adView;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;
    private Button showInterstitialAdButton;
    private Button showRewardedAdButton;

    // IDs de teste do AdMob (substituir pelos IDs gerados na plataforma Google AdMob)
    private static final String BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111";
    private static final String INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
    private static final String REWARDED_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa o SDK do Google Mobile Ads
        MobileAds.initialize(this, initializationStatus -> {});

        adContainerView = findViewById(R.id.ad_view_container);
        showInterstitialAdButton = findViewById(R.id.load_interstitial_button);
        showRewardedAdButton = findViewById(R.id.load_rewarded_button);

        // Configura cliques nos botões para exibir os anúncios
        showInterstitialAdButton.setOnClickListener(v -> showInterstitialAd());
        showRewardedAdButton.setOnClickListener(v -> showRewardedAd());

        // Carrega todos os formatos de anúncios
        loadBanner();
        loadInterstitialAd();
        loadRewardedAd();
    }

    // Carrega o banner e adiciona à interface
    private void loadBanner() {
        adView = new AdView(this);
        adView.setAdUnitId(BANNER_AD_UNIT_ID);
        adView.setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, 360));

        adContainerView.removeAllViews();
        adContainerView.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    // Carrega o anúncio intersticial (anúncio de tela cheia)
    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, INTERSTITIAL_AD_UNIT_ID, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }
        });
    }

    // Carrega o anúncio recompensado
    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, REWARDED_AD_UNIT_ID, adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(RewardedAd rewardedAd) {
                mRewardedAd = rewardedAd;
            }
        });
    }

    // Exibe o anúncio intersticial se carregado
    private void showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
        } else {
            loadInterstitialAd(); // Recarrega se não estiver pronto
        }
    }

    // Exibe o anúncio recompensado e mostra o aviso após assistir
    private void showRewardedAd() {
        if (mRewardedAd != null) {
            mRewardedAd.show(this, rewardItem -> {
                // Usuário assistiu o anúncio até o final
                showRewardDialog();
            });
        } else {
            loadRewardedAd(); // Recarrega se não estiver pronto
        }
    }

    // Mostra uma aviso de recompensa
    private void showRewardDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Parabéns!")
                .setMessage("Você ganhou 5 moedas.")
                .setPositiveButton("Fechar", null)
                .show();
    }

    // Libera recursos do banner ao fechar o app
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adView != null) {
            adView.destroy();
        }
    }
}
