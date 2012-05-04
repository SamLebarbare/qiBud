## Bud Core Bounded Context

Bud is the main Aggregate, composed of:

* BudEntity = JPA
* BudNode = Neo4j
* BudAttachment = MongoGridFS

RootBud == RootEntity, created OnBootstrap.

Seul le cr�ateur d'un Bud peut modifier le coeur de ce Bud.
Les collaborateurs peuvent ajouter des valeurs � ce Bud, le copier, cr�er d'autres Bud � partir de celui-ci.


## Domains are implemented as clients of the Bud Core Bounded Context


## Principals, Realms, Authentication, Authorization


## Store

JPA -> Store
Graph -> Relationship & Graph calculations
MongoGridFS -> Binary content + meta-data

Question : collaboration de transaction entre les stores


## Index

Ecoute le cycle de vie des Bud
Il les indexe

Apache Solr


## Modularity / Flexibility

Package de base = vues d'installation, wizard pour cr�er la base de l'organisation.


---------------------------------

Chaque Bud a �t� cr�� par un Bud.
Ce sont les comportements m�tiers d'un Bud qui peuvent cr�er d'autres Bud.


What defines a Role?

* A r�le name

* Values aggregated in the Bud with validation contraints
* Entities agreggated in the Bud with validation constraints

* Custom behavior as action methods
  * Dedicated Action View

* Dedicated Bud subview
* Dedicated Bud creation and edition subform
* Listeners to host Bud lifecycle


Bud Person
    CoreBud (champs de base + comportement de base)
    List<Role>
        Role Person
            Champs sp�cifiques � une personne.
            Comportement sp�cifique � une personne.
        Role Chef < Person


Bud
    Person
    Project
    Organization
    Mission
    Action

Bud   
    < Person
    < Signer
    < Community
    < Office
    < Folder ( Document, Annexes )
    < Signature
    < Visa



