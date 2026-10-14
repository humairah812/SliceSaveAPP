# 🍕 SliceSave — Save Food, Save Money, Save the Planet

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat&logo=android&logoColor=white)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%20(Material%203)-4285F4?style=flat&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)


**SliceSave** is a modern Android application built using **Jetpack Compose** and **Material 3**. It connects consumers with local restaurants, cafes, and grocery stores to rescue surplus fresh food at steep discounts, fighting food waste while making quality food accessible and affordable.

---

## ✨ Features

### 🍽️ Surplus Food Discovery
- **Categorized Feed**: Browse deals across **Meals**, **Groceries**, and **Cafes & Bakeries**.
- **Urgency & Distance Indicators**: View distance from store (e.g., `0.8 km away`), expiration time (e.g., `Expires in 2 hours`), and original vs. discounted prices.
- **Detailed Item View**: Inspect item descriptions, vendor information, pick-up hours, and allergens before adding to cart.

### 🛒 Cart & Checkout
- **Real-Time Cart Management**: Add or remove items, adjust quantities, and see live order total calculations.
- **Interactive Checkout Flow**: Review rescued meals and confirm pickup orders with clear price breakdowns.

### 🌱 Environmental Impact Tracker
- Quantify your contribution to sustainability directly in your profile:
  - 🍱 **Meals Saved**
  - ⚖️ **Kilograms of Waste Prevented**
  - 💰 **Total Money Saved**
  - 🌍 **Community Milestone**: View collective impact achieved by all SliceSave users.

### 🎁 Rewards & Loyalty Program
- Earn loyalty points with every rescued item.
- Redeem points for direct discounts on subsequent purchases (e.g., *100 points = RM 1 off*).

### 🔐 Authentication & Profile
- Clean and intuitive **Splash**, **Welcome**, **Sign Up**, and **Login** screens.
- Profile management with reward point balances, impact stats, and order history.

---

## 🛠️ Tech Stack & Architecture

- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with [Material 3](https://m3.material.io/)
- **Programming Language**: [Kotlin](https://kotlinlang.org/)
- **Architecture**: MVVM (Model-View-ViewModel) pattern
- **Navigation**: [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- **Lifecycle & State**: `ViewModel`, `remember`, `mutableStateOf`, and `StateFlow`
- **Build System**: Gradle with Kotlin DSL (`build.gradle.kts`) & Version Catalogs (`libs.versions.toml`)

---

## 📂 Project Structure

```text
app/src/main/
├── AndroidManifest.xml
├── java/com/example/sliceapp/
│   ├── MainActivity.kt               # Single-activity host with Compose NavHost
│   ├── data/
│   │   ├── DataSource.kt             # Curated meal, grocery, and cafe surplus items
│   │   ├── Cart.kt                   # Cart state data
│   │   ├── User.kt                   # User session data
│   │   └── model/
│   │       ├── Item.kt               # Item data model
│   │       └── User.kt               # User data model
│   ├── navigation/
│   │   └── Screen.kt                 # Sealed class defining app routes & arguments
│   ├── ui/
│   │   ├── AuthViewModel.kt          # Authentication & user state logic
│   │   ├── CartViewModel.kt          # Cart mutations & checkout state logic
│   │   ├── screens/
│   │   │   ├── SplashScreen.kt       # App launch splash animation screen
│   │   │   ├── WelcomeScreen.kt      # Onboarding welcome screen
│   │   │   ├── LoginScreen.kt        # User authentication screen
│   │   │   ├── SignUpScreen.kt       # New user registration screen
│   │   │   ├── HomeScreen.kt         # Main marketplace feed with category tabs
│   │   │   ├── ProductDetailScreen.kt# Detailed item inspection & add-to-cart
│   │   │   ├── CartScreen.kt         # Order review and price breakdown
│   │   │   └── ProfileScreen.kt      # User stats, rewards & impact card
│   │   └── theme/
│   │       ├── Color.kt              # App color palette (Orange, Green, Gray, etc.)
│   │       ├── Theme.kt              # Material 3 dynamic theme setup
│   │       └── Type.kt               # Typography system
│   └── res/                          # Vector drawables, food photos, and strings
```

---

## 🚀 Getting Started

### Prerequisites
- **Android Studio**: Android Studio Hedgehog / Iguana / Koala / Ladybug or newer.
- **JDK**: Version 11 or 17.
- **Android Device / Emulator**: Running Android 7.0 (API Level 24) or higher.

### Installation & Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/humairah812/SliceSaveAPP.git
   cd SliceSaveAPP
   ```

2. **Open in Android Studio**:
   - Launch Android Studio.
   - Select **Open** and choose the cloned `SliceSaveAPP` folder.
   - Wait for Gradle to finish syncing dependencies.

3. **Run the App**:
   - Connect an Android device with USB debugging enabled or launch an Android Virtual Device (AVD).
   - Press **Run** (`Shift + F10` or the green Play icon) in Android Studio.

---

## 🤝 Contributing

Contributions, bug reports, and feature requests are welcome!
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

