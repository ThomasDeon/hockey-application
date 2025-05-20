import 'package:flutter/material.dart';

class HockeyBackground extends StatelessWidget {
  final Widget child;

  const HockeyBackground({super.key, required this.child});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        image: DecorationImage(
          image: AssetImage('lib/assets/hockey.png'), // Add this asset
          fit: BoxFit.cover,
          colorFilter: ColorFilter.mode(
            Colors.white.withOpacity(0.1),
            BlendMode.dstATop,
          ),
        ),
      ),
      child: child,
    );
  }
}
