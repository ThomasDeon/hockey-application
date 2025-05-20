import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
// import 'package:uuid/uuid.dart';
import '../models/player.dart';
import '../models/team.dart';
import '../providers/player_provider.dart';
import '../providers/team_provider.dart';

class PlayerRegistrationScreen extends StatefulWidget {
  const PlayerRegistrationScreen({super.key});

  @override
  _PlayerRegistrationScreenState createState() =>
      _PlayerRegistrationScreenState();
}

class _PlayerRegistrationScreenState extends State<PlayerRegistrationScreen> {
  final _nameController = TextEditingController();
  final _ageController = TextEditingController();
  String? _selectedPosition;
  String? selectedTeamId;

  // List of allowed hockey positions
  final List<String> _positions = [
    'Center',
    'Left Wing',
    'Right Wing',
    'Defenseman',
    'Offensive Defenseman',
    'Goalie',
  ];

  @override
  void dispose() {
    _nameController.dispose();
    _ageController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Register Player')),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _nameController,
              decoration: InputDecoration(labelText: 'Player Name'),
            ),
            TextField(
              controller: _ageController,
              decoration: InputDecoration(labelText: 'Age'),
              keyboardType: TextInputType.number,
            ),
            DropdownButtonFormField<String>(
              value: _selectedPosition,
              hint: const Text('Select Position'),
              items:
                  _positions.map((position) {
                    return DropdownMenuItem<String>(
                      value: position,
                      child: Text(position),
                    );
                  }).toList(),
              onChanged: (value) {
                setState(() {
                  _selectedPosition = value;
                });
              },
              decoration: const InputDecoration(labelText: 'Position'),
            ),
            StreamBuilder<List<Team>>(
              stream: Provider.of<TeamProvider>(context).getTeams(),
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  List<Team> teams = snapshot.data!;
                  return DropdownButton<String>(
                    value: selectedTeamId,
                    hint: Text('Select Team'),
                    items:
                        teams.map((team) {
                          return DropdownMenuItem<String>(
                            value: team.id,
                            child: Text(team.name),
                          );
                        }).toList(),
                    onChanged: (value) {
                      setState(() {
                        selectedTeamId = value;
                      });
                    },
                  );
                } else {
                  return CircularProgressIndicator();
                }
              },
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () async {
                if (selectedTeamId != null || _selectedPosition != null) {
                  try {
                    final playerProvider = Provider.of<PlayerProvider>(
                      context,
                      listen: false,
                    );
                    final player = Player(
                      id: '',
                      name: _nameController.text,
                      age: int.parse(_ageController.text),
                      position: _selectedPosition!,
                      teamId: selectedTeamId!,
                    );
                    await playerProvider.addPlayer(player);
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(
                        content: Text('Player registered successfully'),
                        backgroundColor: Colors.green,
                        duration: Duration(seconds: 3),
                      ),
                    );
                    Navigator.pop(context);
                  } catch (e) {
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(
                        content: Text('Failed to register player: $e'),
                        backgroundColor: Colors.red,
                      ),
                    );
                  }
                } else {
                  ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(content: Text('Please select a team or position')),
                  );
                }
              },
              child: Text('Register Player'),
            ),
          ],
        ),
      ),
    );
  }
}
