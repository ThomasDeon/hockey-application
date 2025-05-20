import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import '../models/team.dart';
import 'package:uuid/uuid.dart';

class TeamProvider with ChangeNotifier {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;

  Future<void> addTeam(Team team) async {
    // Check for duplicate team name
    final query =
        await _firestore
            .collection('teams')
            .where('name', isEqualTo: team.name)
            .get();
    if (query.docs.isNotEmpty) {
      throw Exception('A team with this name already exists.');
    }

    // Use a generated UUID as the document ID
    final newId = Uuid().v4();
    final newTeam = Team(
      id: newId,
      name: team.name,
      coach: team.coach,
      contact: team.contact,
    );
    await _firestore.collection('teams').doc(newId).set(newTeam.toMap());
    notifyListeners();
  }

  Stream<List<Team>> getTeams() {
    return _firestore
        .collection('teams')
        .snapshots()
        .map(
          (snapshot) =>
              snapshot.docs.map((doc) => Team.fromMap(doc.data())).toList(),
        );
  }

  Stream<List<Team>> get teamsStream => _firestore
      .collection('teams')
      .snapshots()
      .map(
        (snapshot) =>
            snapshot.docs.map((doc) => Team.fromMap(doc.data())).toList(),
      );
}
