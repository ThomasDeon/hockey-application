Namibia Hockey Union Mobile App
This is a mobile application developed for the Namibia Hockey Union to manage hockey-related activities. The app allows users to register teams, manage players, view events, and get real-time news updates. It is built using Flutter for cross-platform compatibility and integrates with Firebase for backend services and NewsAPI for real-time news.

Features

User Authentication: Secure login and registration system with role-based access (Admin, Coach, Player, Fan).
Team Management: Register, update, and manage hockey teams.
Player Management: Register and manage player profiles, including team assignments.
Event Scheduling: Create, view, and manage hockey events and schedules.
Real-time News Updates: Fetch and display the latest hockey-related news using NewsAPI.
Push Notifications: Receive alerts for important updates, such as event reminders and news.
Community Features: Engage with other users through chat and discussion forums.


Setup and Installation
Prerequisites

Flutter SDK: Version 2.0 or higher
Dart SDK: Version 2.12 or higher
Firebase Account: For backend services (authentication, database, notifications)
NewsAPI Key: For fetching real-time news articles

Installation Steps

Clone the Repository:git clone https://github.com/ThomasShikalepo/hockey-application/tree/flutter_variant.git


Navigate to the Project Directory:cd namibia-hockey-app


Install Dependencies:flutter pub get


Set Up Firebase:
Create a Firebase project at Firebase Console.
Add an Android/iOS app to the project and download the configuration file:
For Android: Place google-services.json in android/app/.
For iOS: Place GoogleService-Info.plist in ios/Runner/.


Enable Firebase Authentication, Firestore, and Cloud Messaging in the Firebase Console.


Add NewsAPI Key:
Open lib/utils/constants.dart.
Replace 'YOUR_NEWS_API_KEY_HERE' with your actual NewsAPI key.




Running the App

Connect a Device or Start an Emulator:
Use an Android/iOS device or emulator.


Run the App:flutter run




Project Structure
The project is organized into modular directories for better maintainability:

lib/
main.dart: Entry point of the application.
screens/: Contains screen widgets (e.g., login_screen.dart, home_screen.dart, news_screen.dart).
models/: Data models (e.g., team.dart, player.dart, news_article.dart).
providers/: State management using Provider (e.g., auth_provider.dart, news_provider.dart).
services/: Services for external interactions (e.g., database_service.dart, api_service.dart).
widgets/: Reusable UI components (e.g., team_card.dart, news_card.dart).
utils/: Utility files (e.g., constants.dart, helpers.dart).



This structure ensures a clean separation of concerns, making the codebase easy to navigate and extend.

Contributing
We welcome contributions to improve the app. To contribute:

Fork the Repository.
Create a New Branch for your feature or bugfix.
Commit Your Changes and push to your fork.
Create a Pull Request to the main repository.

Please ensure your code follows the project's coding standards and includes appropriate tests.

License
This project is licensed under the MIT License. See the LICENSE file for details.
