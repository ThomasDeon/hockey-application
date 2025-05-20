import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import '../models/player.dart';
import 'package:uuid/uuid.dart';

class PlayerProvider with ChangeNotifier {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;

  Future<void> addPlayer(Player player) async {
    // Check for duplicate player name within the same team
    final query =
        await _firestore
            .collection('players')
            .where('teamId', isEqualTo: player.teamId)
            .where('name', isEqualTo: player.name)
            .get();
    if (query.docs.isNotEmpty) {
      throw Exception('A player with this name already exists in the team.');
    }

    // Use a generated UUID as the document ID
    final newId = Uuid().v4();
    final newPlayer = Player(
      id: newId,
      name: player.name,
      age: player.age,
      position: player.position,
      teamId: player.teamId,
    );
    await _firestore.collection('players').doc(newId).set(newPlayer.toMap());
    notifyListeners();
  }

  Stream<List<Player>> getPlayers() {
    return _firestore
        .collection('players')
        .snapshots()
        .map(
          (snapshot) =>
              snapshot.docs.map((doc) => Player.fromMap(doc.data())).toList(),
        );
  }
}
