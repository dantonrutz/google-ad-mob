# Guia de Configuração do Google AdMob no Android Studio (Java)

> [📖 Acesse a documentação oficial](https://developers.google.com/admob/android/quick-start?hl=pt-br)

---

## Passo 1 – Criar Conta e Aplicativo no AdMob

1. Acesse: [Google AdMob](https://admob.google.com/intl/pt-BR_br/home/)
2. Faça login com sua conta Google.
3. No canto superior esquerdo, clique em **"Apps" > "Adicionar app"**.
4. Siga os passos para registrar seu aplicativo.
5. Após finalizar, copie o **ID do seu aplicativo**. Ele começa com: **ca-app-pub-913...**
> **Importante:** Guarde esse ID — será usado na configuração do seu app Android.

---

## Passo 2 – Adicionar o SDK do Google Mobile Ads

### 1. No arquivo `build.gradle.kts`, adicione a dependência:
```java
implementation("com.google.android.gms:play-services-ads:24.2.0")
```

### 2. No `AndroidManifest.xml`, adicione a seguinte meta tag dentro da tag `<application>`:
```java
<meta-data
    android:name="com.google.android.gms.ads.APPLICATION_ID"
    android:value="ID_DO_SEU_APP" />
```
> **Importante:** Substitua ID_DO_SEU_APP pelo ID copiado no Passo 1 (ex: ca-app-pub-913...).

---

## Passo 3 – Criar Blocos de Anúncio

1. No painel do Google AdMob, vá em **"Blocos de anúncios"** no menu lateral.
2. Clique em **"Adicionar bloco de anúncio"**.
3. Escolha um dos formatos disponíveis:

### Tipos de anúncios disponíveis:

- **Banner:** pequeno anúncio fixado geralmente no topo ou rodapé do app.
- **Interstitial (Tela cheia):** ocupa a tela inteira, normalmente exibido em transições.
- **Recompensado (Rewarded):** usuário assiste um vídeo em troca de recompensa.
- **Native (Nativo):** personalizado de acordo com o layout do app.
- **App Open:** aparece ao abrir o app.

---

## Neste exemplo, utilizamos 3 tipos de anúncios:

- **Banner**
- **Interstitial (Tela cheia)**
- **Recompensado (Rewarded)**

Para cada anúncio criado, o AdMob irá gerar um ID único de bloco de anúncio: algo como, **ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx**.
Esse ID é essencial, pois é com ele que o seu aplicativo irá solicitar e exibir os anúncios correspondentes.
Você deve copiar esse ID e colá-lo no seu código Java onde for inicializar e carregar o respectivo tipo de anúncio.
Cada tipo de anúncio possui uma forma específica de ser exibido e o uso correto do ID garante que o AdMob contabilize impressões, cliques e recompensas corretamente.
