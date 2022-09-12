from collections import namedtuple

import neat

NeatElement = namedtuple('NeatElement', ['genome', 'net', 'id'])


def run(config_file):
    """
    runs the NEAT algorithm to train a neural network to play flappy bird.
    :param config_file: location of config file
    :return: None
    """
    config = neat.config.Config(neat.DefaultGenome, neat.DefaultReproduction,
                                neat.DefaultSpeciesSet, neat.DefaultStagnation,
                                config_file)
    p = neat.Checkpointer.restore_checkpoint('neat-checkpoint-48')
    # p = neat.Population(config)
    p.add_reporter(neat.StdOutReporter(True))
    stats = neat.StatisticsReporter()
    p.add_reporter(stats)
    p.add_reporter(neat.Checkpointer(50))
    p.run(score_in_java, 10000)


def score_in_java(genomes, config):
    index = 0
    for genome_id, genome in genomes:
        net = neat.nn.FeedForwardNetwork.create(genome, config)
        save_neat_as_coefficients(NeatElement(genome, net, genome_id), "coefficients/"+str(index) + ".txt")
        index += 1
    with open("communication.txt", 'w') as f:
        f.write("java")
    while True:
        with open("communication.txt") as f:
            if f.read() == "python":
                break
    with open("response.txt") as f:
        scores = f.read().split(',')
    index = 0
    for genome_id, genome in genomes:
        genome.fitness = float(scores[index])
        index += 1


def save_neat_as_coefficients(neatElement, name):
    biases = []
    for key in neatElement[0].nodes:
        biases.append(neatElement[0].nodes[key].bias)
    coefficients = []
    for evaluation in neatElement[1].node_evals:
        coefficients.append([])
        for coef in evaluation[5]:
            coefficients[-1].append(coef[1])
    to_save = []
    for index, coef in enumerate(coefficients):
        to_save.append(coef + [biases[index]])
    with open(name, 'w') as file:
        file.write("\n".join(",".join(tuple(map(lambda elem: str(elem), line))) for line in to_save))


if __name__ == '__main__':
    run("configuration_two.txt")
