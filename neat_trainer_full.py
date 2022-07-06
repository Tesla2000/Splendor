from collections import namedtuple
import pickle
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
    # p = neat.Checkpointer.restore_checkpoint('neat-checkpoint-13')
    p = neat.Population(config)
    p.add_reporter(neat.StdOutReporter(True))
    stats = neat.StatisticsReporter()
    p.add_reporter(stats)
    p.add_reporter(neat.Checkpointer(10))
    p.run(score_in_java, 100)


def score_in_java(genomes, config):
    for index, genome_id, genome in enumerate(genomes):
        net = neat.nn.FeedForwardNetwork.create(genome, config)
        save_neat_as_coefficients(NeatElement(genome, net, genome_id), str(index) + ".txt")
    with open("communication.txt", 'w') as f:
        f.write("java")
    while True:
        with open("communication.txt") as f:
            if f.read() == "python":
                break
    with open("response.txt") as f:
        scores = f.read().split(',')
    for index, genome_id, genome in enumerate(genomes):
        genome.genome.fitness = float(scores[index])


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
        for line in to_save:
            file.write(",".join(tuple(map(lambda elem: str(elem), line))))
        file.write("\n")


if __name__ == '__main__':
    with open("pickles/master1.pickle", 'rb') as file:
        master = pickle.load(file)
    save_neat_as_coefficients(master, "coefficients/master_coefs.txt")
