import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/player_provider.dart';
import '../models/player.dart';
import '../widgets/player_card.dart';
import '../widgets/hockey_background.dart';

class PlayerListScreen extends StatelessWidget {
  final String teamId;
  final String teamName;

  PlayerListScreen({required this.teamId, required this.teamName});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Row(
          children: [
            Image.asset('/lib/assets/hockey.png', height: 40),
            SizedBox(width: 10),
            Text('Players - $teamName'),
          ],
        ),
        backgroundColor: Colors.blue[900],
      ),
      body: HockeyBackground(
        child: StreamBuilder<List<Player>>(
          stream: Provider.of<PlayerProvider>(context).getPlayers(),
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              List<Player> players = snapshot.data!
                  .where((player) => player.teamId == teamId)
                  .toList();
              if (players.isEmpty) {
                return Center(child: Text('No players found for this team.'));
              }
              return ListView.builder(
                itemCount: players.length,
                itemBuilder: (context, index) {
                  return PlayerCard(player: players[index]);
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
    );
  }
}