class Player {
  final String id;
  final String name;
  final int age;
  final String position;
  final String teamId;

  Player({
    required this.id,
    required this.name,
    required this.age,
    required this.position,
    required this.teamId,
  });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'age': age,
      'position': position,
      'teamId': teamId,
    };
  }

  factory Player.fromMap(Map<String, dynamic> map) {
    return Player(
      id: map['id'],
      name: map['name'],
      age: map['age'],
      position: map['position'],
      teamId: map['teamId'],
    );
  }
}
