#include <vector>
using namespace std;

#ifndef NODE_H
#define NODE_H

class Node {
    public:
        Node(const char* name, int x, int y);
        void addNode(const Node& , int);
        Node getNodeByName(const char*);
        int getWeight(const char*);
        int getWeight(const Node&);
        const char* getName()
        {
            return this->name;
        }
        vector<Node> getConnections()
        {
            return this->conn;
        }
    private:
        int x;
        int y;
        char* name;
        vector<Node> conn;
        vector<int>weights;
        void setName(const char*);
        void setXandY(const int x, const int y);

};

#endif // NODE_H
