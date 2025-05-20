class Team {
  final String id;
  final String name;
  final String coach;
  final String contact;
  final String? logoUrl;

  Team({
    required this.id,
    required this.name,
    required this.coach,
    required this.contact,
    this.logoUrl,
  });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'coach': coach,
      'contact': contact,
      'logoUrl': logoUrl,
    };
  }

  factory Team.fromMap(Map<String, dynamic> map) {
    return Team(
      id: map['id'],
      name: map['name'],
      coach: map['coach'],
      contact: map['contact'],
      logoUrl: map['logoUrl'],
    );
  }
}
