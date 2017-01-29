/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('GROUPS', {
    group_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    subject_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    group_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    group_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'GROUP_TYPES',
        key: 'type_id'
      }
    },
    created_time: {
      type: DataTypes.DATE,
      allowNull: false
    },
    admin_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    description: {
      type: DataTypes.STRING,
      allowNull: false
    },
    group_photo_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'PHOTOS',
        key: 'photo_id'
      }
    },
    is_private: {
      type: DataTypes.INTEGER(1),
      allowNull: false
    }
  }, {
    tableName: 'GROUPS'
  });
};
