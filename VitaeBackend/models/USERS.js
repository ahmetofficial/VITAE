/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('USERS', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      primaryKey: true
    },
    user_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    user_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_TYPES',
        key: 'user_type_id'
      }
    },
    mail: {
      type: DataTypes.STRING,
      allowNull: false
    },
    password: {
      type: DataTypes.STRING,
      allowNull: false
    },
    mail_activation: {
      type: DataTypes.INTEGER(1),
      allowNull: false
    },
    created_time: {
      type: DataTypes.DATE,
      allowNull: false
    },
    phone_number: {
      type: DataTypes.STRING,
      allowNull: true
    },
    about_me: {
      type: DataTypes.STRING,
      allowNull: true
    },
    friend_count: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    is_official_user: {
      type: DataTypes.INTEGER(1),
      allowNull: false
    },
    relationship_status_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_RELATIONSHIP_STATUS',
        key: 'relationship_status_id'
      }
    }
  }, {
    tableName: 'USERS'
  });
};
