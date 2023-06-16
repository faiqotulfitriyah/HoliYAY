from flask import jsonify
from bson import ObjectId
from tensorflow.keras import layers
import tensorflow as tf
import tqdm
import string
import re
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import io
import pickle
import numpy as np
import sys
import json
import requests
import pandas as pd
import certifi
import pymongo

connection_url = 'mongodb+srv://sanyadika:sany123@cluster0.xgxzwaq.mongodb.net/HoliYAY?retryWrites=true&w=majority'
client = pymongo.MongoClient(connection_url, tlsCAFile=certifi.where())
# Database
Database = client.get_database('HoliYAY')
# Table
collection = Database.locations


# replace 'yourfile.csv' with your uploaded file name
# df = pd.read_csv(
#     'https://storage.googleapis.com/capstone-c23-ps182/tourism_with_id.csv')
query = collection.find()
df = pd.DataFrame(list(query))
# Preprocessing the descriptions
df = df['Description'].apply(lambda x: x.lower().split())

"""# model"""


# SEED = 42
# AUTOTUNE = tf.data.AUTOTUNE

# sentence = "The wide road shimmered in the hot sun"
# tokens = list(sentence.lower().split())
# print(len(tokens))

# vocab, index = {}, 1  # start indexing from 1
# vocab['<pad>'] = 0  # add a padding token
# for token in tokens:
#     if token not in vocab:
#         vocab[token] = index
#         index += 1
# vocab_size = len(vocab)
# print(vocab)

# inverse_vocab = {index: token for token, index in vocab.items()}
# print(inverse_vocab)

# example_sequence = [vocab[word] for word in tokens]
# print(example_sequence)

# window_size = 2
# positive_skip_grams, _ = tf.keras.preprocessing.sequence.skipgrams(
#     example_sequence,
#     vocabulary_size=vocab_size,
#     window_size=window_size,
#     negative_samples=0)
# print(len(positive_skip_grams))

# for target, context in positive_skip_grams[:5]:
#     print(
#         f"({target}, {context}): ({inverse_vocab[target]}, {inverse_vocab[context]})")

# # Get target and context words for one positive skip-gram.
# target_word, context_word = positive_skip_grams[0]

# # Set the number of negative samples per positive context.
# num_ns = 4

# context_class = tf.reshape(tf.constant(context_word, dtype="int64"), (1, 1))
# negative_sampling_candidates, _, _ = tf.random.log_uniform_candidate_sampler(
#     true_classes=context_class,  # class that should be sampled as 'positive'
#     num_true=1,  # each positive skip-gram has 1 positive context class
#     num_sampled=num_ns,  # number of negative context words to sample
#     unique=True,  # all the negative samples should be unique
#     range_max=vocab_size,  # pick index of the samples from [0, vocab_size]
#     seed=SEED,  # seed for reproducibility
#     name="negative_sampling"  # name of this operation
# )
# print(negative_sampling_candidates)
# print([inverse_vocab[index.numpy()] for index in negative_sampling_candidates])

# # Reduce a dimension so you can use concatenation (in the next step).
# squeezed_context_class = tf.squeeze(context_class, 1)

# # Concatenate a positive context word with negative sampled words.
# context = tf.concat([squeezed_context_class, negative_sampling_candidates], 0)

# # Label the first context word as `1` (positive) followed by `num_ns` `0`s (negative).
# label = tf.constant([1] + [0]*num_ns, dtype="int64")
# target = target_word

# sampling_table = tf.keras.preprocessing.sequence.make_sampling_table(size=60)
# print(sampling_table)

# # Generates skip-gram pairs with negative sampling for a list of sequences
# # (int-encoded sentences) based on window size, number of negative samples
# # and vocabulary size.


# def generate_training_data(sequences, window_size, num_ns, vocab_size, seed):
#     # Elements of each training example are appended to these lists.
#     targets, contexts, labels = [], [], []

#     # Build the sampling table for `vocab_size` tokens.
#     sampling_table = tf.keras.preprocessing.sequence.make_sampling_table(
#         vocab_size)

#     # Iterate over all sequences (sentences) in the dataset.
#     for sequence in tqdm.tqdm(sequences):

#         # Generate positive skip-gram pairs for a sequence (sentence).
#         positive_skip_grams, _ = tf.keras.preprocessing.sequence.skipgrams(
#             sequence,
#             vocabulary_size=vocab_size,
#             sampling_table=sampling_table,
#             window_size=window_size,
#             negative_samples=0)

#         # Iterate over each positive skip-gram pair to produce training examples
#         # with a positive context word and negative samples.
#         for target_word, context_word in positive_skip_grams:
#             context_class = tf.expand_dims(
#                 tf.constant([context_word], dtype="int64"), 1)
#             negative_sampling_candidates, _, _ = tf.random.log_uniform_candidate_sampler(
#                 true_classes=context_class,
#                 num_true=1,
#                 num_sampled=num_ns,
#                 unique=True,
#                 range_max=vocab_size,
#                 seed=seed,
#                 name="negative_sampling")

#             # Build context and label vectors (for one target word)
#             context = tf.concat([tf.squeeze(context_class, 1),
#                                 negative_sampling_candidates], 0)
#             label = tf.constant([1] + [0]*num_ns, dtype="int64")

#             # Append each element from the training example to global lists.
#             targets.append(target_word)
#             contexts.append(context)
#             labels.append(label)

#     return targets, contexts, labels


# def column_to_txt(query, column_name, txt_file):
#     df = pd.DataFrame(list(query))
#     column = df[column_name]
#     column.to_csv(txt_file, index=False, header=False)


# # Usage
# # Replace with the path to your CSV file
# query = collection.find()
# # Replace with the index of the column you want to extract (0-based index)
# column_index = 'Description'
# # Replace with the desired path for the TXT file
# txt_file = 'tourism_with_id.txt'

# column_to_txt(query, column_index, txt_file)

# with open('tourism_with_id.txt', 'r', encoding='utf-8', errors='replace') as f:
#     lines = f.read().splitlines()
# for line in lines[:20]:
#     print(line)

# text_ds = tf.data.TextLineDataset(
#     'tourism_with_id.txt').filter(lambda x: tf.cast(tf.strings.length(x), bool))

# # Now, create a custom standardization function to lowercase the text and
# # remove punctuation.


# def custom_standardization(input_data):
#     lowercase = tf.strings.lower(input_data)
#     return tf.strings.regex_replace(lowercase,
#                                     '[%s]' % re.escape(string.punctuation), '')


# # Define the vocabulary size and the number of words in a sequence.
# vocab_size = 10000
# sequence_length = 100

# # Use the `TextVectorization` layer to normalize, split, and map strings to
# # integers. Set the `output_sequence_length` length to pad all samples to the
# # same length.
# vectorize_layer = layers.TextVectorization(
#     standardize=custom_standardization,
#     max_tokens=vocab_size,
#     output_mode='int',
#     output_sequence_length=sequence_length)

# vectorize_layer.adapt(text_ds.batch(1024))

# # Save the created vocabulary for reference.
# inverse_vocab = vectorize_layer.get_vocabulary()
# print(inverse_vocab[:20])

# # Vectorize the data in text_ds.
# text_vector_ds = text_ds.batch(1024).prefetch(
#     AUTOTUNE).map(vectorize_layer).unbatch()

# sequences = list(text_vector_ds.as_numpy_iterator())
# print(len(sequences))

# for seq in sequences[:5]:
#     print(f"{seq} => {[inverse_vocab[i] for i in seq]}")

# targets, contexts, labels = generate_training_data(
#     sequences=sequences,
#     window_size=2,
#     num_ns=4,
#     vocab_size=vocab_size,
#     seed=SEED)

# targets = np.array(targets)
# contexts = np.array(contexts)
# labels = np.array(labels)

# print('\n')
# print(f"targets.shape: {targets.shape}")
# print(f"contexts.shape: {contexts.shape}")
# print(f"labels.shape: {labels.shape}")

# BATCH_SIZE = 1024
# BUFFER_SIZE = 10000
# dataset = tf.data.Dataset.from_tensor_slices(((targets, contexts), labels))
# dataset = dataset.shuffle(BUFFER_SIZE).batch(BATCH_SIZE, drop_remainder=True)
# print(dataset)

# dataset = dataset.cache().prefetch(buffer_size=AUTOTUNE)
# print(dataset)


# class Word2Vec(tf.keras.Model):
#     def __init__(self, vocab_size, embedding_dim):
#         super(Word2Vec, self).__init__()
#         self.target_embedding = layers.Embedding(vocab_size,
#                                                  embedding_dim,
#                                                  input_length=1,
#                                                  name="w2v_embedding")
#         self.context_embedding = layers.Embedding(vocab_size,
#                                                   embedding_dim,
#                                                   input_length=num_ns+1)

#     def call(self, pair):
#         target, context = pair
#         # target: (batch, dummy?)  # The dummy axis doesn't exist in TF2.7+
#         # context: (batch, context)
#         if len(target.shape) == 2:
#             target = tf.squeeze(target, axis=1)
#         # target: (batch,)
#         word_emb = self.target_embedding(target)
#         # word_emb: (batch, embed)
#         context_emb = self.context_embedding(context)
#         # context_emb: (batch, context, embed)
#         dots = tf.einsum('be,bce->bc', word_emb, context_emb)
#         # dots: (batch, context)
#         return dots


# embedding_dim = 200
# word2vec = Word2Vec(vocab_size, embedding_dim)
# word2vec.compile(optimizer='adam',
#                  loss=tf.keras.losses.CategoricalCrossentropy(
#                      from_logits=True),
#                  metrics=['accuracy'])

# tensorboard_callback = tf.keras.callbacks.TensorBoard(log_dir="logs")

# word2vec.fit(dataset, epochs=500, callbacks=[tensorboard_callback])

# weights = word2vec.get_layer('w2v_embedding').get_weights()[0]
# vocab = vectorize_layer.get_vocabulary()

# out_v = io.open('vectors.tsv', 'w', encoding='utf-8')
# out_m = io.open('metadata.tsv', 'w', encoding='utf-8')

# for index, word in enumerate(vocab):
#     if index == 0:
#         continue  # skip 0, it's padding.
#     vec = weights[index]
#     out_v.write('\t'.join([str(x) for x in vec]) + "\n")
#     out_m.write(word + "\n")
# out_v.close()
# out_m.close()

# # try:
# #   from google.colab import files
# #   files.download('vectors.tsv')
# #   files.download('metadata.tsv')
# # except Exception:
# #   pass

# """#Infer 1"""

# vectors = pd.read_csv('vectors.tsv', sep='\t', header=None)

# vectors

# vectors_list = vectors.values.tolist()
# vectors_array = np.array(vectors_list)

# metadata = pd.read_csv('metadata.tsv', sep='\t', header=None)

# metadata

# metadata.values[:6639]

# vectors_dict = {}
# for i, row in enumerate(metadata.values):
#     vectors_dict[row[0]] = vectors_array[i]

# """bersehin df"""

# # df = pd.read_csv('tourism_with_id.csv')
# query = collection.find()
# df = pd.DataFrame(list(query))
# df_clean = df[['_id',
#                'Place_Name',
#                'Description',
#                'Category',
#                'Price',
#                'City',
#                'Image',
#                'Coordinate',
#                'Lat',
#                'Long',
#                'Rating']]
# df_clean

# desc_vs = []
# df['Description'] = df['Description'].apply(lambda x: x.lower().split())
# for desc in df['Description']:
#     desc_v = [vectors_dict[token]
#               for token in desc if token in vectors_dict.keys()]
#     desc_vs.append(np.array(desc_v).mean(axis=0))

# df_clean['Description_vecs'] = desc_vs
# # df_clean


# # Save the dictionary to a pickle file
# with open('./vector.pickle', 'wb') as pickle_file:
#     pickle.dump(vectors_dict, pickle_file)


# # Save the dictionary to a pickle file
# with open('./df_clean.pickle', 'wb') as pickle_file:
#     pickle.dump(df_clean, pickle_file)


# # Save the dictionary to a pickle file
# with open('./vector.pickle', 'wb') as pickle_file:
#     pickle.dump(vectors_dict, pickle_file)

"""# FIX INFER"""


# Assuming you have a pickle file called 'data.pickle' that you want to load
file_path = 'vector.pickle'

# Load the dictionary from the pickle file
with open(file_path, 'rb') as pickle_file:
    vectors_dict = pickle.load(pickle_file)


# Assuming you have a pickle file called 'data.pickle' that you want to load
file_path = 'df_clean.pickle'

# Load the dictionary from the pickle file
with open(file_path, 'rb') as pickle_file:
    df_clean = pickle.load(pickle_file)


def vectorize(sentences):
    word_v = [vectors_dict[token]
              for token in sentences if token in vectors_dict.keys()]
    return np.array(word_v).mean(axis=0)


class JSONEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, ObjectId):
            return str(o)
        return json.JSONEncoder.default(self, o)


def recommend_destinations(user_input, city=None):
    # Filter destinations based on city if city is not None
    if city is not None:
        city_df = df_clean[df_clean['City'] == city]
    else:
        city_df = df

    # Transform the user input into the same vector space
    user_input_vector = vectorize(user_input.lower().split())

    # Calculate the cosine similarity between the user input vector and the description_vectors
    city_description_vectors = np.stack(
        city_df['Description_vecs'].values, axis=1).T
    cosine_sim = cosine_similarity(
        [user_input_vector], city_description_vectors)

    # Get the indices of the destinations sorted by their similarity to the user input
    sim_scores = list(enumerate(cosine_sim[0]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)

    # Get the indices of the top 3 most similar destinations
    destination_indices = [i[0] for i in sim_scores[:10]]

    locations = []
    for i in destination_indices:
        # locations.append({'id': city_df['_id'].iloc[i],
        #                  'place_name': city_df['Place_Name'].iloc[i],
        #                   'description': city_df['Description'].iloc[i],
        #                   'rating': city_df['Rating'].iloc[i]})
        locations.append({
            '_id': JSONEncoder().encode(city_df['_id'].iloc[i]).replace('\"', ""),
            # 'place_id': int(city_df['Place_Id'].iloc[i]),
            'place_name': city_df['Place_Name'].iloc[i],
            'description': city_df['Description'].iloc[i],
            'category': city_df['Category'].iloc[i],
            'city': city_df['City'].iloc[i],
            'price': int(city_df['Price'].iloc[i]),
            'rating': city_df['Rating'].iloc[i],
            #   'time_minutes': city_df['Time_Minutes'].iloc[i],
            'coordinate': city_df['Coordinate'].iloc[i],
            'lat': city_df['Lat'].iloc[i],
            'long': city_df['Long'].iloc[i],
            'image': city_df['Image'].iloc[i]})
        print(city_df)

   # Return the top 3 most similar destinations
    return locations
# Test the function with a user input


# print(recommend_destinations(sys.argv[1], city=sys.argv[2]))

# locations = recommend_destinations(sys.argv[1], city=sys.argv[2])
# for location in locations:
# print(location)
