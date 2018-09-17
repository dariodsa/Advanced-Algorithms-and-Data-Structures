#include "node.h"
#include <string.h>
#include <stdlib.h>

Node::Node(const char* name,int x, int y)
{
    setName(name);
    setXandY(x,y);
}

void Node::setXandY(const int x, const int y)
{
    this->x = x;
    this->y = y;
}

void Node::setName(const char* name)
{
    this->name = (char*)malloc(sizeof(char) * strlen(name));
    strcpy(this->name, name);
}

int Node::getWeight(const Node& node)
{
    return getWeight(node.name);
}

int Node::getWeight(const char* name)
{
    for(unsigned long i = 0, len = conn.size(); i < len; ++i)
    {
        if(strcmp(name, conn[i].getName()) == 0)
        {
            return this->weights[i];
        }
    }
    return -1;
}



void Node::addNode(const Node &node, int weight)
{
    this->conn.push_back(node);
    this->weights.push_back(weight);
}
Node Node::getNodeByName(const char* name)
{
    for(unsigned long i=0, len = this->conn.size(); i < len; ++i)
    {
        if(strcmp(this->conn[i].getName(), name) == 0)
        {
            return this->conn[i];
        }
    }
    return nullptr;
}

