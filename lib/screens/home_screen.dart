import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/auth_provider.dart';
import 'team_registration_screen.dart';
import 'player_registration_screen.dart';
import 'event_list_screen.dart';
import 'news_screen.dart';
import 'team_list_screen.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final authProvider = Provider.of<AuthProvider>(context, listen: false);

    return Scaffold(
      appBar: AppBar(
        title: Text('Namibia Hockey App'),
        actions: [
          IconButton(
            icon: Icon(Icons.logout),
            onPressed: () async {
              try {
                await authProvider.logout();
              } catch (e) {
                ScaffoldMessenger.of(
                  context,
                ).showSnackBar(SnackBar(content: Text('Logout failed: $e')));
              }
            },
          ),
        ],
      ),
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage('lib/assets/hockey.png'),
            fit: BoxFit.cover,
            colorFilter: ColorFilter.mode(
              Colors.white.withOpacity(0.1),
              BlendMode.dstATop,
            ),
          ),
        ),
        child: Center(
          child: GridView.count(
            crossAxisCount: MediaQuery.of(context).size.width > 600 ? 3 : 2,
            padding: EdgeInsets.all(16.0),
            crossAxisSpacing: 16.0,
            mainAxisSpacing: 16.0,
            shrinkWrap: true,
            children: [
              _buildFeatureButton(
                context,
                'Register Team',
                Icons.group_add,
                () => Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => TeamRegistrationScreen()),
                ),
              ),
              _buildFeatureButton(
                context,
                'Register Player',
                Icons.person_add,
                () => Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => PlayerRegistrationScreen()),
                ),
              ),
              _buildFeatureButton(
                context,
                'View Events',
                Icons.event,
                () => Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => EventListScreen()),
                ),
              ),
              _buildFeatureButton(
                context,
                'News & Updates',
                Icons.article,
                () => Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => NewsScreen()),
                ),
              ),
              _buildFeatureButton(
                context,
                'View',
                Icons.visibility,
                () => Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => TeamListScreen()),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildFeatureButton(
    BuildContext context,
    String title,
    IconData icon,
    VoidCallback onPressed,
  ) {
    return Card(
      elevation: 4,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12.0)),
      child: InkWell(
        onTap: onPressed,
        borderRadius: BorderRadius.circular(12.0),
        child: Padding(
          padding: EdgeInsets.all(16.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Icon(icon, size: 40, color: Colors.blue, semanticLabel: title),
              SizedBox(height: 8),
              Text(
                title,
                style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                textAlign: TextAlign.center,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
