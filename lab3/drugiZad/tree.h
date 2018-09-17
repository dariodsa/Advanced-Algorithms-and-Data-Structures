#ifndef TREE_H
#define TREE_H

#include <vector>
#include "node.h"

class Tree
{
public:
    Tree(const char*);
    void addNode(const Node& node);

private:
    std::vector<Node> nodes;
    void initTree(const char*);

};

#endif // TREE_H
