#include "tree.h"

#include <stdio.h>
#include <stdlib.h>

Tree::Tree(const char* filePath)
{
    initTree(filePath);
}

void Tree::addNode(const Node& node)
{
    this->nodes.push_back(node);
}

void Tree::initTree(const char* filePath)
{

}
