import 'package:cloud_firestore/cloud_firestore.dart';
import '../models/team.dart';
import '../models/player.dart';
import '../models/event.dart';

class DatabaseService {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;

  // Add a team to Firestore
  Future<void> addTeam(Team team) async {
    await _firestore.collection('teams').doc(team.id).set(team.toMap());
  }

  // Add a player to Firestore
  Future<void> addPlayer(Player player) async {
    await _firestore.collection('players').doc(player.id).set(player.toMap());
  }

  // Add an event to Firestore
  Future<void> addEvent(Event event) async {
    await _firestore.collection('events').doc(event.id).set(event.toMap());
  }

  // Get teams from Firestore
  Stream<List<Team>> getTeams() {
    return _firestore.collection('teams').snapshots().map((snapshot) =>
        snapshot.docs.map((doc) => Team.fromMap(doc.data())).toList());
  }

  // Get players from Firestore
  Stream<List<Player>> getPlayers() {
    return _firestore.collection('players').snapshots().map((snapshot) =>
        snapshot.docs.map((doc) => Player.fromMap(doc.data())).toList());
  }

  // Get events from Firestore
  Stream<List<Event>> getEvents() {
    return _firestore.collection('events').snapshots().map((snapshot) =>
        snapshot.docs.map((doc) => Event.fromMap(doc.data())).toList());
  }
}