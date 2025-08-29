import 'package:flutter/material.dart';
import 'package:flutter/services.dart'; // Para SystemNavigator.pop
// Para fazer requisições HTTP, se for usar a lógica real de back-end
// import 'package:http/http.dart' as http;

void main() {
  runApp(const AsclepiusApp());
}

class AsclepiusApp extends StatelessWidget {
  const AsclepiusApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Asclepius',
      theme: ThemeData(
        primarySwatch: Colors.red,
        fontFamily: 'PressStart2P', // Definindo a fonte pixelada como padrão
        textTheme: const TextTheme(
          // Ajuste o tamanho da fonte para se adequar ao estilo pixelado
          headlineLarge: TextStyle(fontFamily: 'PressStart2P', fontSize: 48, color: Colors.black),
          headlineMedium: TextStyle(fontFamily: 'PressStart2P', fontSize: 32, color: Colors.black),
          bodyLarge: TextStyle(fontFamily: 'PressStart2P', fontSize: 16, color: Colors.black),
          bodyMedium: TextStyle(fontFamily: 'PressStart2P', fontSize: 14, color: Colors.black),
          labelLarge: TextStyle(fontFamily: 'PressStart2P', fontSize: 18, color: Colors.white),
        ),
      ),
      home: const LoadingScreen(),
    );
  }
}

class LoadingScreen extends StatefulWidget {
  const LoadingScreen({super.key});

  @override
  State<LoadingScreen> createState() => _LoadingScreenState();
}

class _LoadingScreenState extends State<LoadingScreen> with SingleTickerProviderStateMixin {
  late AnimationController _controller;
  late Animation<double> _animation;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      vsync: this,
      duration: const Duration(seconds: 3), // Duração simulada do carregamento
    );

    _animation = Tween<double>(begin: 0.0, end: 1.0).animate(_controller)
      ..addListener(() {
        setState(() {});
      });

    _controller.forward().whenComplete(() {
      if (mounted) {
        Navigator.of(context).pushReplacement(
          MaterialPageRoute(builder: (context) => const MainScreen()),
        );
      }
    });

    _loadGameData();
  }

  Future<void> _loadGameData() async {
    // --- LÓGICA DE CARREGAMENTO DO GRAFO NO BACK-END (JAVA) ---
    // Substitua este Future.delayed pela sua chamada HTTP real.
    // final response = await http.get(Uri.parse('http://your-java-backend.com/api/load-graph'));
    // if (response.statusCode == 200) {
    //   // Grafo carregado com sucesso
    // } else {
    //   // Tratar erro
    // }
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // Imagem de fundo estática na tela de carregamento (cruz e nome)
          Positioned.fill(
            child: Image.asset(
              'assets/images/asclepius_splash.png',
              fit: BoxFit.cover, // Cobrirá toda a tela
            ),
          ),
          // Conteúdo de carregamento posicionado abaixo do nome "Asclepius"
          Align(
            alignment: Alignment.bottomCenter, // Alinha à parte inferior e centraliza horizontalmente
            child: Padding(
              padding: const EdgeInsets.only(bottom: 80.0), // Ajusta a distância do fundo
              child: Column(
                mainAxisSize: MainAxisSize.min, // Ocupa o mínimo de espaço vertical necessário
                children: [
                  Text(
                    'Carregando...',
                    style: Theme.of(context).textTheme.bodyLarge?.copyWith(color: Colors.black),
                  ),
                  const SizedBox(height: 20),
                  SizedBox(
                    width: MediaQuery.of(context).size.width * 0.7, // Largura da barra
                    child: Stack(
                      children: [
                        LinearProgressIndicator(
                          value: _animation.value,
                          backgroundColor: Colors.grey[300],
                          valueColor: const AlwaysStoppedAnimation<Color>(Colors.red),
                          minHeight: 20,
                        ),
                        // Ícone/Ambulância animada sobre a barra
                        Positioned(
                          left: _animation.value * (MediaQuery.of(context).size.width * 0.7 - 40), // 40 é a largura aproximada da ambulância
                          top: -10, // Ajuste vertical para posicionar o ícone acima da barra
                          child:
                          // Se você tiver a imagem, descomente a linha abaixo e comente o Icon
                          // Image.asset(
                          //   'assets/images/small_ambulance.png', // Seu arquivo real de ambulância
                          //   width: 40,
                          //   height: 40,
                          // ),
                          Icon( // Placeholder temporário
                            Icons.local_hospital,
                            color: Colors.red,
                            size: 40,
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class MainScreen extends StatelessWidget {
  const MainScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // Removida a AppBar para não exibir a faixa superior "Asclepius"
      body: Container(
        // Background meio vermelho e meio branco
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
            colors: [Colors.red, Colors.red, Colors.white, Colors.white], // Vermelho no topo, branco na parte inferior
            stops: [0.0, 0.5, 0.5, 1.0], // Transição nítida no meio
          ),
        ),
        child: Center( // Centraliza o conteúdo (imagem e botões) na tela
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center, // Centraliza verticalmente
            crossAxisAlignment: CrossAxisAlignment.center, // Centraliza horizontalmente
            children: [
              // Imagem "Asclepius" agora menor e centralizada
              Image.asset(
                'assets/images/asclepius_splash.png',
                width: MediaQuery.of(context).size.width * 0.6, // Diminuindo o tamanho da imagem
                fit: BoxFit.contain, // Garante que a imagem se ajuste ao espaço sem cortar
              ),
              const SizedBox(height: 60), // Espaço entre a imagem e os botões
              ElevatedButton(
                onPressed: () {
                  Navigator.of(context).push(
                    MaterialPageRoute(builder: (context) => const SimulationScreen()),
                  );
                },
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.white,
                  padding: const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
                child: Text(
                  'Simular',
                  style: Theme.of(context).textTheme.labelLarge?.copyWith(color: Colors.red),
                ),
              ),
              const SizedBox(height: 30),
              ElevatedButton(
                onPressed: () {
                  SystemNavigator.pop(); // Fecha o aplicativo
                },
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.grey[800],
                  padding: const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
                child: Text(
                  'Sair',
                  style: Theme.of(context).textTheme.labelLarge?.copyWith(color: Colors.white),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class SimulationScreen extends StatelessWidget {
  const SimulationScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Simulação do Mapa',
          style: Theme.of(context).textTheme.headlineMedium?.copyWith(color: Colors.white),
        ),
        backgroundColor: Colors.red,
        centerTitle: true,
      ),
      body: Center(
        child: SingleChildScrollView( // Permite rolagem se o mapa for maior que a tela
          child: Image.asset(
            'assets/images/mapa_do_tiled.png', // Substitua pelo caminho da sua imagem do mapa
            fit: BoxFit.contain,
          ),
        ),
      ),
    );
  }
}
