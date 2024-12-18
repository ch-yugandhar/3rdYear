def greedy_best_first_search(start, stop):
    open, closed = {start}, set()
    parents = {start: None}
    
    while open:
        n = min(open, key=heuristic)  # Choose node with lowest heuristic
        
        if n == stop:  # Goal found
            path = []
            while n:
                path.append(n)
                n = parents[n]
            print("Path found:", " --> ".join(path[::-1]))
            return path[::-1]
        
        open.remove(n)
        closed.add(n)
        
        for m, _ in Graph.get(n, []):
            if m not in open | closed:
                open.add(m)
                parents[m] = n
    
    print("Path does not exist!")
    return None

def heuristic(n):
    return {'S': 5, 'A': 3, 'B': 4, 'C': 2, 'D': 6, 'G': 0}[n]

Graph = {
    'S': [('A', 1)],
    'A': [('B', 2), ('C', 1)],
    'B': [('D', 5)],
    'C': [('D', 3), ('G', 4)],
    'D': [('G', 2)],
}

greedy_best_first_search('S', 'G')
