import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/team_provider.dart';
import '../models/team.dart';
import 'player_list_screen.dart';
import '../widgets/team_card.dart';
import '../widgets/hockey_background.dart';

class TeamListScreen extends StatefulWidget {
  @override
  _TeamListScreenState createState() => _TeamListScreenState();
}

class _TeamListScreenState extends State<TeamListScreen> {
  String _searchQuery = '';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Row(
          children: [
            Image.asset('/lib/assets/hockey.png', height: 40),
            SizedBox(width: 10),
            Text('Teams'),
          ],
        ),
        backgroundColor: Colors.blue[900],
      ),
      body: HockeyBackground(
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: TextField(
                decoration: InputDecoration(
                  labelText: 'Search Teams',
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10.0),
                  ),
                  filled: true,
                  fillColor: Colors.white.withOpacity(0.8),
                  suffixIcon: Icon(Icons.search),
                ),
                onChanged: (value) {
                  setState(() {
                    _searchQuery = value.toLowerCase();
                  });
                },
              ),
            ),
            Expanded(
              child: StreamBuilder<List<Team>>(
                stream: Provider.of<TeamProvider>(context).getTeams(),
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    List<Team> teams = snapshot.data!;
                    List<Team> filteredTeams = teams
                        .where((team) =>
                            team.name.toLowerCase().contains(_searchQuery))
                        .toList();
                    if (filteredTeams.isEmpty) {
                      return Center(child: Text('No teams found.'));
                    }
                    return ListView.builder(
                      itemCount: filteredTeams.length,
                      itemBuilder: (context, index) {
                        return TeamCard(
                          team: filteredTeams[index],
                          onTap: () {
                            Navigator.push(
                              context,
                              MaterialPageRoute(
                                builder: (_) => PlayerListScreen(
                                  teamId: filteredTeams[index].id,
                                  teamName: filteredTeams[index].name,
                                ),
                              ),
                            );
                          },
                        );
                      },
                    );
                  } else if (snapshot.hasError) {
                    return Center(child: Text('Error: ${snapshot.error}'));
                  } else {
                    return Center(child: CircularProgressIndicator());
                  }
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}