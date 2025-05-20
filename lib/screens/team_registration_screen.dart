import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:uuid/uuid.dart';
import '../providers/team_provider.dart';
import '../models/team.dart';

class TeamRegistrationScreen extends StatefulWidget {
  const TeamRegistrationScreen({super.key});

  @override
  _TeamRegistrationScreenState createState() => _TeamRegistrationScreenState();
}

class _TeamRegistrationScreenState extends State<TeamRegistrationScreen> {
  final _nameController = TextEditingController();
  final _coachController = TextEditingController();
  final _contactController = TextEditingController();
  final _logoUrlController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    final teamProvider = Provider.of<TeamProvider>(context);

    return Scaffold(
      appBar: AppBar(title: Text('Register Team')),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _nameController,
              decoration: InputDecoration(labelText: 'Team Name'),
            ),
            TextField(
              controller: _coachController,
              decoration: InputDecoration(labelText: 'Coach Name'),
            ),
            TextField(
              controller: _contactController,
              decoration: InputDecoration(labelText: 'Contact Info'),
            ),
            TextField(
              controller: _logoUrlController,
              decoration: const InputDecoration(labelText: 'Team Logo URL (optional)'),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () async {
                try {
                  final logoUrl = _logoUrlController.text.isNotEmpty ? _logoUrlController.text : null;
                  final team = Team(
                    id: Uuid().v4(),
                    name: _nameController.text,
                    coach: _coachController.text,
                    contact: _contactController.text,
                    logoUrl: logoUrl,
                  );
                  await teamProvider.addTeam(team);
                  ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(content: Text('Team registered successfully')),
                  );
                  Navigator.pop(context);
                } catch (e) {
                  ScaffoldMessenger.of(
                    context,
                  ).showSnackBar(SnackBar(content: Text(e.toString())));
                }
              },
              child: Text('Register Team'),
            ),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    _nameController.dispose();
    _coachController.dispose();
    _contactController.dispose();
    _logoUrlController.dispose();
    super.dispose();
  }
}
